package com.wyb.thread.base.aqs;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

import sun.misc.Unsafe;

/**
 * @author Marcher丶
 */
public class WybLock {

    /**
     * 当前加锁状态，
     */
    private volatile int state = 0;

    private Thread lockHolder;

    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        if (acquire()) {
            return;
        }
        waiters.add(currentThread);
        for (;;) {
            if (currentThread == waiters.peek() && acquire()) {
                waiters.poll();
                return;
            }
            // 让出cpu使用权
            LockSupport.park(currentThread);
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        if (currentThread != lockHolder) {
            throw new RuntimeException("lockholder is not current thread");
        }
        int state = getState();
        if (compareAndSwapState(state, 0)) {
            setLockHolder(null);
            Thread firstThread = waiters.peek();
            if (null != firstThread) {
                LockSupport.unpark(firstThread);
            }
        }
    }

    /**
     * 尝试加锁
     */
    public boolean acquire() {
        Thread thread = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            if ((waiters.size() == 0 || thread == waiters.peek()) && compareAndSwapState(0, 1)) {
                setLockHolder(thread);
                return true;
            }
        }
        return false;

    }

    public final boolean compareAndSwapState(int except, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, except, update);
    }

    public static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(WybLock.class.getDeclaredField("state"));
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

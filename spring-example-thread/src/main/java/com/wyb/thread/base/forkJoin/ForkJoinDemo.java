package com.wyb.thread.base.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Marcher丶
 * RecursiveAction：用于没有返回结果的任务。
 * RecursiveTask ：用于有返回结果的任务。
 */
public class ForkJoinDemo extends RecursiveTask<Integer> {

    private static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) <= threshold) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(start, middle);
            ForkJoinDemo rightTask = new ForkJoinDemo(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行完，并得到其结果
            Integer rightResult = rightTask.join();
            Integer leftResult = leftTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo countTask = new ForkJoinDemo(1, 200);
        //第一次提交任务给forkJoinPool时是在无限循环for (;;)中入队。第一步先检查workQueues是不是还没有创建，如果没有，则进行创建。
        //之后跳到外层for循环并随机选取workQueues里面一个队列，并判断队列是否已创建。
        //没有创建，则进行创建！后又跳到外层for循环直到选到一个非空队列并且加锁成功！这样最后才把任务入队~。
        //所以我们知道fork/join的任务队列workQueues并不是初始化的时候就创建好了，而是在有任务提交的时候才创建！并且每次入队时都需要利用cas操作来进行加锁和释放锁！
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(countTask);
        System.out.println(forkJoinTask.get());
    }
}


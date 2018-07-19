# spring 多线程编程
### 一些概念：
并行与并发：

- 并行：多个cpu实例或者多台机器同时执行一段处理逻辑，是**真正的同时**。 
- 并发：通过cpu调度算法，让用户看上去同时执行，实际上从cpu操作层面不是真正的同时。并发往往在场景中有公用的资源，那么针对这个公用的资源往往产生瓶颈，我们会用TPS或者QPS来反应这个系统的处理能力。
- 线程安全：经常用来描绘一段代码。指在并发的情况之下，该代码经过多线程使用，线程的调度顺序不影响任何结果。这个时候使用多线程，我们只需要关注系统的内存，cpu是不是够用即可。反过来，线程不安全就意味着线程的调度顺序会影响最终结果。
- 同步：Java中的同步指的是通过人为的控制和调度，保证共享资源的多线程访问成为线程安全，来保证结果的准确。如上面的代码简单加入@synchronized关键字。在保证结果准确的同时，提高性能，才是优秀的程序。线程安全的优先级高于性能。
- volatile：多线程的内存模型：main memory（主存）、working memory（线程栈），在处理数据时，线程会把值从主存load到本地栈，完成操作后再save回去(volatile关键词的作用：每次针对该变量的操作都激发一次load and save)。
针对多线程使用的变量如果不是volatile或者final修饰的，很有可能产生不可预知的结果（另一个线程修改了这个值，但是之后在某线程看到的是修改之前的值）。其实道理上讲同一实例的同一属性本身只有一个副本。但是多线程是会缓存值的，本质上，volatile就是不去缓存，直接取值。在线程安全的情况下加volatile会牺牲性能。

### 线程状态&状态切换

![线程状态转换](http://upload-images.jianshu.io/upload_images/4942449-8f4ad7b6ac7009c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 

对象方法：
- wait()：当前线程放弃对象锁，使该线程处于**等待池**(wait blocked pool),直到notify()/notifyAll()，线程被唤醒被放到**锁定池**(lock blocked pool )，释放同步锁使线程回到可运行状态（Runnable）。
- notify():从对象的等待池中移走一个任意的线程并放到锁标志等待池中，只有锁标志等待池中线程能够获取锁标志；如果锁标志等待池中没有线程，则notify()不起作用。
- notifyAll(): notifyAll()则从对象等待池中移走所有等待那个对象的线程并放到锁标志等待池中。

Thread线程方法：
- yield():正在执行的线程把运行机会交给线程池中拥有相同优先级的线程，无法保证迅速转换，运行状态转到可运行状态.
- join():使得一个线程在另一个线程结束后再执行。在一个线程中调用other.join(),将等待other执行完后才继续本线程。
- sleep():不会释放对象锁，暂停一段时间。
- interrupt()：后两个函数皆可以被打断。

使用condition控制线程通信：
- await(),类似wait()
- signal() 类似notify
- signalAll()类似notifyAll

### 高级多线程控制类：
1.ThreadLocal类:

2.原子类（AtomicInteger、AtomicBoolean……）

3.Lock类　
- Condition
- ReentrantLock
- ReentrantReadWriteLock.ReadLock
- ReentrantReadWriteLock.WriteLock

4.容器类
- BlockingQueue
- ConcurrentHashMap

5.管理类
- ThreadPoolExecutor

核心线程满了，接下来进队列，队列也满了，创建新线程，直到达到最大线程数，之后再超出，会进入拒绝rejectedExecution


- JMX框架下的系统级管理类 ThreadMXBean
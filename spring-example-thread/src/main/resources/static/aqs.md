# AQS探究

## 先来看看类信息

### 前世今生

()[]

只继承了xx，这个之后再分析。

### 原作者的想法

再来看看类注释，源码中很长一段我就不贴了，自己去源码中看。这里我提取一下重要的信息。

1.该类是一个用于实现FIFO等待队列的阻塞锁和相关同步器（信号量，事件等）的框架。
子类允许持有其他状态字段，但是必须以原子性更新state值。

2.不具体实现，提供方法供具体实现调用。

3.默认支持独占模式和共享模式。

4.嵌套了ConditionObject，提供了条件锁的同步实现，
实现了Condition接口，并且实现了其中的await(),signal(),signalALL()等方法。

### 来看看拥有哪些变量

### 主要变量，还有一些cas方法的就不看啦。

```java
/** 
* 队列的头结点，懒加载的。只能在初始化和setHead方法的时候改变
* 如果头结点存在，则保证它的等待状态不是CANCELLED
* waitStatus在内部类node中定义
*/
private transient volatile Node head;

/**
 * 等待队列的尾部，延迟初始化。 仅通过方法enq进行修改以添加新的等待节点。
 */
private transient volatile Node tail;

/**
 * 同步器状态
 */
private volatile int state;

/**
* 旋转的纳秒阈值
*/
static final long spinForTimeoutThreshold = 1000L;
```

### Node内部类，FIFO阻塞队列的节点。
```java
/** 共享模式下正在等待的节点标识 */
static final Node SHARED = new Node();
/** 排它模式下正在等待的节点标识 */
static final Node EXCLUSIVE = null;
/** 线程取消的状态 */
static final int CANCELLED =  1;
/** 后续线程需要释放的状态 */
static final int SIGNAL    = -1;
/** 线程需要等待条件的状态 */
static final int CONDITION = -2;
/** 下一个acquireShared应该无条件传播状态 */
static final int PROPAGATE = -3;
/**
* SIGNAL:当前节点的后续节点被阻塞或者即将被阻塞，在释放或者取消当前节点的时候必须取消它之后的节点的park状态。
* 为了避免冲突，首先验证signal信号再通过cas获取。在取消请求，或者释放共享资源使用。
* CANCELLED：由于超时或中断，该节点被取消。 节点永远不会离开此状态。 特别是，具有取消节点的线程永远不会再次阻塞。
* CONDITION：该节点当前在条件队列中。 在传输之前，它不会用作同步队列节点，此时状态将设置为0。（此值的使用与该字段的其他用途无关，但简化了机制。）
* PROPAGATE:releaseShared应该传播到其他节点。 在doReleaseShared中对此进行了设置（仅适用于头节点），以确保传播继续进行，即使此后进行了其他操作也是如此。
* 0:以上都不是
* 这些值以数字方式排列以简化使用。
* 非负值表示节点不需要发信号。 因此，大多数代码不需要检查特定值，仅需检查符号即可。
* 对于普通同步节点，该字段初始化为0；对于条件节点，该字段初始化为CONDITION。 使用CAS（或在可能的情况下进行无条件的易失性写操作）对其进行修改。
*/
volatile int waitStatus;
/**
* 当前节点的前节点，入队时分配，出队时清空。在前置节点为空时，进入循环，成功acquire后成功头节点。
*/
volatile Node prev;

volatile Node next;

/*让该节点排队的线程。 在构造上初始化，使用后消失。*/
volatile Thread thread;

Node nextWaiter;
```



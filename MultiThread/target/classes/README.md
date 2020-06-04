1. 使用Runnable,Callable接口的方式创建线程的优缺点

    - 线程类只是实现了Runnable,Callable接口，还可以继承其他类
    - 这种方式，多个Threads可以共享一个target对象，适合多个相同线程处理同一个资源，从而将CPU，代码和数据分开，形成清晰的模型。
    - 缺点，编程稍微复杂，必须使用Thread.currentThread()访问当前线程
    
2. 继承Thread创建多线程的优缺点
    - 缺点，已经继承Thread类，无法继承其他类
    - 优点是简单
    
一般推荐使用接口实现

----

1. 线程的生命周期

    - 新建、就绪、运行、阻塞、死亡
    - new 创建一个线程，这个时候就处于新建状态
    - 调用start()后，进程处于就绪状态
    - 只有处于新建状态的线程才能调用start()
    
tips: 如果希望子线程调用start()开始执行，可以使用Thread.sleep(1)睡眠一下，JVM调用另一个就绪线程。

2. 下面的情况下，线程会进入阻塞状态
    - 调用sleep()方法主动放弃所占用的处理器资源
    - 线程调用了阻塞式的IO方法，在该方法返回之前，线程阻塞
    - 线程试图获取一个同步监视器，但该同步监视器被其他线程拥有
    - 线程正在等待某个通知（notify）
    - 调用了suspend()将线程挂起，这种方法容易死锁，避免使用
    
3. 针对上面阻塞的情况，发生下面的时间，解除阻塞到就绪状态
    - sleep()方法的线程经过了指定的时间
    - 阻塞式IO方法已经返回
    - 成功获取了试图取得的同步监视器
    - 其他线程发出了一个通知
    - 挂起状态的线程调用了resume()方法
    
4. 线程死亡的情况
    - run() 或 call()方法完成，线程正常结束
    - 线程抛出一个未捕获的异常或错误
    - 直接调用stop()结束线程，容易死锁，不建议使用
    
----

1. join()
2. 后台线程
3. sleep()和yield()区别
    - sleep()暂停当前线程，会给其他进程机会，不会理会其他线程的优先级，但yield()只会给优先级相同，或优先级更高机会
    - sleep()执行后，线程变为阻塞状态，经过阻塞时间才回转为就绪状态，yield()不会进入阻塞状态，而是强行进入就绪状态，因此可能刚调用yield()后，由继续倍调用执行。
    - sleep()需要捕获异常，而yield()不需要
    - sleep()比yield()有更好移植性，不建议使用yield()控制并发进程的执行。
    
----

1. 线程同步
    - 同步代码块
    - 同步方法 synchronized 修饰方法 同步监视器为当前对象this
        - 该类对象可以被多个线程安全的访问
        - 每个线程调用对象的方法都能得到正确的结果
        - 调用任意方法后，对象依然保持合理状态
        
        - 不可变类总是安全的，因为对象状态不可改变，可变类是线程安全的
    - 释放同步监视器的锁定，无法主动释放，下面的情况会释放
        - 当前线程的同步方法、同步代码块执行结束，当前线程释放同步监视器
        - 当前在同步代码块、同步方法中遇到了break、return中止了当前代码块、方法的执行
        - 当前线程在同步代码块、同步方法中出现了未处理的异常或错误，导致方法异常结束
        - 当前线程执行同步代码块、同步方法时，线程执行了同步监视器对象的wait(),当前线程暂停，并释放同步监视器
        
    - 下面情况不会释放同步监视器
        - 执行同步代码块、同步方法时，程序调用Thread.sleep()、Thread.yield()暂停当前线程，不会释放同步监视器
        - 执行同步代码块、同步方法时，其他线程调用了该线程的suspend()
        
2. 同步锁
    > Lock控制多个线程对共享资源进行访问的工具。每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象。
    
    Java8 增加了新型的SampledLock类，大多数情况下可以替换传统的ReentrantReadWriteLock。
    ReentrantReadWriteLock为读写操作提供了三种锁模式：Writing、ReadingOptimistic、Reading。
    
    在实现线程安全，常用的是ReentranLock(可重入锁)
    > 可重入锁：一个线程可以对已经加锁的ReentrantLock锁再次加锁，ReentrantLock对象会维持一个计数器来最终lock()的嵌套调用
    必须显示调用unlock()解锁，所以一段被锁的代码可以调用另一个被相同锁保护的方法。

----

- wait(),导致当前线程等待，直到其他线程调用同步监视器的notify()或notifyAll()方法来唤醒线程
- notify(),唤醒此同步监视器上某个等待的线程
- notifyAll(),唤醒此同步监视器上的所有线程

使用Condition线程通信(和Lock配套)

使用BlockingQueue线程通信

---

线程池
Java5以后，提供了Executors工厂类来创建线程池

- newCachedThreadPool()
- newFixedThreadPool(int nThreads)
- newSingleThreadPool() 等价上面制定nThreads = 1
- newScheduledThreadPool(int corePoolSize) ：可以延迟执行
- newSingleScheduledThreadPool()
- newWorkStealingPool(int parallelism) 创建足够的线程池来满足给定的并行级别
- newWorkStealingPool() 根据CPU来创建

前三个返回ExecutorService
中间两个返回ScheduledExecutorService
最后两个生成work stealing池，相当于后台线程池

---
包装线程不安全的集合

Java集合，ArrayList、LinkedList、HastSet、TreeSet、HashMap、TreeMap

使用Collections提供的类方法可以将上面包装成线程安全的集合

- synchronizedCollection()
- synchronizedList()
- synchronizedMap()
- synchronizedSet()
- synchronizedSortedMap()
- synchronizedSortedSet()

线程安全的集合类
Java5   java.util.concurrent包下提供
- Concurrent开头，ConcurrentHashMap、ConcurrentSkipListMap、ConcurrentSkipListSet、ConcurrentLinkedQueue、ConcurrentLinkedDeque
- CopyOnWrite开头，CopyOnWriteArraySet、CopyOnWriteArrayList

Concurrent开头支持并发访问，写入线程的操作是安全的，但读取操作不确定。

当多个线程共享访问一个公共集合ConcurrentLinkedQueue是合适的
ConcurrentHashMap默认支持16个线程并发访问，超过16个可能会等待。

CopyOnWriteArrayList适合读取操作远远大于写入操作的场景，比如缓存。
底层采用复制底层数组来实现写操作，因此写入性能较差。


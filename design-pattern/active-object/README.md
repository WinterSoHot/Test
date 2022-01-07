# 意图

> The active object design pattern decouples method execution from method invocation for objects that each reside in their thread of control. The goal is to introduce concurrency, by using asynchronous method invocation, and a scheduler for handling requests.

活动对象设计模式将方法的执行与对象的方法调用解耦，这些对象各自驻留在其控制线程中。其目的是通过使用异步方法调用和处理请求的调度器来引入并发性。

# 解释

> The class that implements the active object pattern will contain a self-synchronization mechanism without using 'synchronized' methods.

实现活动对象模式的类将包含一个自我同步机制，而不使用 "同步 "方法。
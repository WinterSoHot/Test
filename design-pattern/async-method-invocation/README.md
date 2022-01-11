# 意图


> Asynchronous method invocation is a pattern where the calling thread
is not blocked while waiting results of tasks. The pattern provides parallel
processing of multiple independent tasks and retrieving the results via
callbacks or waiting until everything is done.

异步方法执行是一个非阻塞执行等待结果返回，让多个独立的任务能够并行执行，并且通过回调或者等待完成获取结果。


# 解释
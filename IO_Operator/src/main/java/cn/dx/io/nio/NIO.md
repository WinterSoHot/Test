NIO 和传统的IO都是用于输入输出，而NIO使用了不同的方式来管理输入输出，
NIO采用内存映射文件的方式来管理。

- Channel 通道：传输数据
    
    三种常用的方法
    - map()
    - read()
    - write()
- Buffer 缓存：保存数据
    - capacity： 缓冲区的容量
    - limit： 第一个不应该倍读出或者写入的缓冲区位置索引
    - position：指明下一个可以倍读取或写入的缓冲区索引
    - mark: 直接将position定位到mark标记
    
    值的关系
    0<= mark <= position <= limit <= capacity
    
    两个重要方法
    - flip() 将前position设置为limit，position修改为0，表示已经做好输出的准备
    - clear() 将 position为0，limit设置为capacity，表示已经做好输入的准备。
- Selector: 支持非阻塞输入输出 

NIO.2 Java 1.7 
- 全面的文件IO和文件系统访问支持
- 基于异步的Channel IO
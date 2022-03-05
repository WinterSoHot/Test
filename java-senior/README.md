# Java 进阶

## core

### 字节码

```shell
javap -c .\target\classes\cn\dx\java\senior\core\HelloByteCode.class
```

```shell
Compiled from "HelloByteCode.java"
public class cn.dx.java.senior.core.HelloByteCode {
  public cn.dx.java.senior.core.HelloByteCode();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class cn/dx/java/senior/core/HelloByteCode
       3: dup
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: return
}
```

助记符显示

```shell
javap -c -verbose.\target\classes\cn\dx\java\senior\core\HelloByteCode.class
```

```shell
Classfile /D:/Projects/IdeaProjects/Test/java-senior/target/classes/cn/dx/java/senior/core/HelloByteCode.class
  Last modified 2022-2-15; size 462 bytes
  MD5 checksum 52c4f882c9ed4dea6e0419b0d61f50e3
  Compiled from "HelloByteCode.java"
public class cn.dx.java.senior.core.HelloByteCode
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#19         // java/lang/Object."<init>":()V
   #2 = Class              #20            // cn/dx/java/senior/core/HelloByteCode
   #3 = Methodref          #2.#19         // cn/dx/java/senior/core/HelloByteCode."<init>":()V
   #4 = Class              #22            // java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               LocalVariableTable
  #10 = Utf8               this
  #11 = Utf8               Lcn/dx/java/senior/core/HelloByteCode;
  #12 = Utf8               main
  #13 = Utf8               ([Ljava/lang/String;)V
  #14 = Utf8               args
  #15 = Utf8               [Ljava/lang/String;
  #16 = Utf8               demo
  #17 = Utf8               SourceFile
  #18 = Utf8               HelloByteCode.java
  #19 = NameAndType        #5:#6          // "<init>":()V
  #20 = Utf8               cn/dx/java/senior/core/HelloByteCode
  #21 = Utf8               java/lang/Object
{
  public cn.dx.java.senior.core.HelloByteCode();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 7: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcn/dx/java/senior/core/HelloByteCode;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: new           #2                  // class cn/dx/java/senior/core/HelloByteCode
         3: dup
         4: invokespecial #3                  // Method "<init>":()V
         7: astore_1
         8: return
      LineNumberTable:
        line 9: 0
        line 10: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  args   [Ljava/lang/String;
            8       1     1  demo   Lcn/dx/java/senior/core/HelloByteCode;
}
SourceFile: "HelloByteCode.java"
```

更加详细的显示，包括常量池，局部变量表，行号表（作为调试定位），版本号，方法信息

Java字节码指令由单字节的指令组成，理论上最多支持256个操作码

根据指令的性质，可以分为四大类：
1. 栈操作指令，包括与局部变量交互的指令
2. 程序流程控制指令
3. 对象操作指令，包括方法调用指令
4. 算术运算以及类型转换指令

字节码的运行时结构
> JVM 是一台基于栈的计算机器。
> 每个线程都有一个独属于自己的线程栈（JVM Stack），用于存储
栈帧（Frame）。
> 每一次方法调用，JVM 都会自动创建一个栈帧。
> 栈帧由操作数栈， 局部变量数组以及一个 Class 引用组成。Class 引用 指向当前方法在运行时常量池中对应的 Class。

### 类加载器

类的生命周期：
1. 加载： 找 Class 文件
2. 验证： 验证格式、依赖
3. 准备： 静态字段、方法表 
4. 解析： 符号解析为应用
5. 初始化： 构造器、静态变量赋值、静态代码块
6. 使用
7. 卸载

类的加载时机
1. 当虚拟机启动时，初始化用户指定的主类，就是启动执行的 main 方法所在的类；
2. 当遇到用以新建目标类实例的 new 指令时，初始化 new 指令的目标类，就是 new
   一个类的时候要初始化；
3. 当遇到调用静态方法的指令时，初始化该静态方法所在的类；
4. 当遇到访问静态字段的指令时，初始化该静态字段所在的类；
5. 子类的初始化会触发父类的初始化；
6. 如果一个接口定义了 default 方法，那么直接实现或者间接实现该接口的类的初始化，
   会触发该接口的初始化；
7. 使用反射 API 对某个类进行反射调用时，初始化这个类，其实跟前面一样，反射调用
   要么是已经有实例了，要么是静态方法，都需要初始化；
8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的
   类。

类不会初始化，可能会加载的情况
1. 通过子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化。
2. 定义对象数组，不会触发该类的初始化。
3. 常量在编译期间会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不
   会触发定义常量所在的类。
4. 通过类名获取 Class 对象，不会触发类的初始化，Hello.class 不会让 Hello 类初始
   化。
5. 通过 Class.forName 加载指定类时，如果指定参数 initialize 为 false 时，也不会触
   发类初始化，其实这个参数是告诉虚拟机，是否要对类进行初始化。Class.forName
   （“jvm.Hello”）默认会加载 Hello 类。
6. 通过 ClassLoader 默认的 loadClass 方法，也不会触发初始化动作（加载了，但是
   不初始化）。

加载器分类
- 启动类加载器 BootstrapClassLoader
- 扩展类加载器：ExtClassLoader
- 应用类加载器： AppClassLoader

加载器特点
- 双亲委派
- 负责依赖
- 缓存加载

添加引用类的方式
- 放到JDK的lib/ext下，或者-Djava.ext.dirs
- java -cp/classpath 或者 class 文件放到当前路径
- 自定义ClassLoader加载
- 拿到当前执行类的ClassLoader，反射调用addUrl方法添加Jar或路径

### 内存模型

每个线程都只能访问自己的线程栈

每个线程都不能访问其他线程的局部变量

所有原生类型的局部变量都存储在线程栈中，因此对其他线程是不可见的

线程可以将一个原生变量值的副本传给另一个线程，单不能共享原生局部变量本身

堆内存中包含了Java代码中所创建的所有对象，不管是那个线程创建的。其中也包含了包装类型。

不管是创建一个对象并将其赋值给局部变量，还是赋值给另一个对象的成员对象，创建的对象都被保存在堆内存中。

如果是原生数据类型的局部变量，那么它的内容保存在线程栈上。

如果是对象引用，则栈中的局部变量槽位中保存着对象的引用地址，而实际的对象内容保存在堆中。

对象的成员变量与对象本身一起存储在堆上，不管成员变量的类型是原生数值，还是对象引用。

类的静态变量则和类定义一样保存在堆中。

总结：
- 方法中使用的原生数据类型和对象引用地址在栈上存储；对象、对象成员与类定义、静态变量在堆上。
- 堆内存又称为共享堆，堆中所有对象，可以被所有线程访问，只要他们能拿到对象的引用地址。
- 如果一个线程可以访问某个对象时，也就可以访问该对象的成员变量。
- 如果两个线程同时调用某个对象的同一方法，则它们都可以访问到这个对象的成员变量，但每个线程的局部变量副本是独立的。

每启动一个线程，JVM就会在栈空间分配对象的线程栈。 比如1mb的空间（-Xss1m）

线程栈也称Java方法栈。如果是JNI方法，则会分配一个单独的本地方法栈

线程在执行过程中，会有多个方法组成调用栈，每执行一个方法，就会创建对象的栈帧

栈帧是一个逻辑上的概念，是对方法调用的抽象。大小在一个方法编写完成就能确定。

堆内存是所有线程共用的内存中间。

分为：年轻代和老年代

年轻代分为新生代和存活区。大部分的GC算法都有2个存活区 S0 S1

Non-Heap本质上还是堆，一般不归GC管理。里面划分为三个内存池
- Metaspace 
- CCS Compressed Class Space 存放class信息和Metaspace有交叉
- Code Cache, 存放JIT编译器编译后的本地机器代码

CPU和内存行为
- CPU乱序执行
- volatile关键字
- 原子性操作
- 内存屏障

JMM

JMM规范明确定义了不同线程之间，通过那些方式，在什么时候可以看见其他线程保存到共享变量中的值；以及在必要时，如何对共享变量的访问进行同步。
屏蔽各种硬件平台和操作系统的内存访问差异，实现了Java并发程序的跨平台

- 所有对象，static变量，以及数组，都必须放到堆内存中
- 局部变量，方法的形参，异常处理语句的入参不允许在线程之间共享，所有不受内存模型的影响。
- 多个线程同时对一个变量访问时，只要存在线程执行写操作，那么这就称为“冲突”
- 可以被其他线程影响或感知的操作，称为线程间的交互行为，可以分为读取、写入、同步操作、外部操作等等。
- 同步操作包括：对volatile变量的读写，对monitor的锁定和解锁，线程的起始操作和结尾操作，线程的启动和结束等等
- 外部操作是指对线程执行环境之外的操作，比如停止其他线程等等。
- JMM规范的是线程间的交互操作，而不管线程内部对局部变量进行的操作。

### 启动参数

1. 系统属性参数
   - -Dfile.encoding=UTF-8
   - -Duser.timezone=GMT+08
   - -Dmaven.test.skip=true
   - -Dio.netty.eventLoopThreads=8

   ```
   System.getProperty(key)
   ```
2. 运行模式参数
   - -server 服务器模式，启动慢，性能好
   - -client 客户端模式，启动快，性能差
   - -Xlint 解释模式，强制JVM解释执行字节码
   - Xcomp 和解释模式相反，将字节码编译成本地代码
   - Xmixed 混合模式
3. 堆内存设置参数
   - Xmx 最大堆内存，这个内存不包含栈内存，也不包含堆外使用的内存，一般最大为系统物理内存的70%
   - Xms 堆内存空间的初始大小。专用服务要保证和Xmx一致，不然会启动就产生FullGC，在堆内存动态扩容，产生性能抖动
   - Xmn 等价 -XX:NewSize 官方建议设置为 -Xmx的1/2-1/4
   - XX: MaxPermSize=size
   - XX: MaxMetaspaceSize=size
   - XX: MaxDirectMemorySize=size 系统可用最大堆外内存
   - Xss 设置每个线程栈的字节数 和 XX: ThreadStackSize
4. GC设置参数
   - XX: +UseG1GC
   - XX: +UseConcMarkSweepGC
   - XX: +UseSerialGC
   - XX: +UseParallelGC
5. 分析诊断参数
   - XX：+-HeapDumpOnOutOfMemoryError 选项, 当 OutOfMemoryError 产生，即内存溢出(堆内存或持久代)时，自动 Dump 堆内存。
      
     示例用法： java -XX:+HeapDumpOnOutOfMemoryError -Xmx256m ConsumeHeap
   - XX：HeapDumpPath 选项, 与 HeapDumpOnOutOfMemoryError 搭配使用, 指定内存溢出时 Dump 文件的目 录。 如果没有指定则默认为启动 Java 程序的工作目录。
      
      示例用法： java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/local/ ConsumeHeap
      自动 Dump 的 hprof 文件会存储到 /usr/local/ 目录下。
   - XX：OnError 选项, 发生致命错误时（fatal error）执行的脚本。 例如, 写一个脚本来记录出错时间, 执行一些命令, 或者 curl 一下某个在线报警的 url.
      
      示例用法：java -XX:OnError="gdb - %p" MyApp
      可以发现有一个 %p 的格式化字符串，表示进程 PID。 -XX：OnOutOfMemoryError 选项, 抛出 OutOfMemoryError 错误时执行的脚本。
   - XX：ErrorFile=filename 选项, 致命错误的日志文件名,绝对路径或者相对路径。
   - Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=1506，远程调试
6. JavaAgent参数

   Agent 是 JVM 中的一项黑科技, 可以通过无侵入方式来做很多事情，比如注入 AOP 代码，执行统
   计等等，权限非常大。这里简单介绍一下配置选项，详细功能需要专门来讲。
   设置 agent 的语法如下: -agentlib:libname[=options] 启用 native 方式的 agent, 参考 LD_LIBRARY_PATH 路径。
   -agentpath:pathname[=options] 启用 native 方式的 agent。 -javaagent:jarpath[=options] 启用外部的 agent 库, 比如 pinpoint.jar 等等。
   -Xnoagent 则是禁用所有 agent。
   以下示例开启 CPU 使用时间抽样分析:
   JAVA_OPTS="-agentlib:hprof=cpu=samples,file=cpu.samples.log"
package cn.dx.java.jvm;

/**
 * 双亲委派模型：
 * 在加载当前类的时候，首先检查当前类是否已经加载过，加载过就直接返回。
 * 首先将该加载请求委托给父类加载器的loadClass()处理，如果加载失败，才自己进行处理。
 * <p>
 * 双亲委派模型保证了Java程序的稳定运行，可以避免类的重复加载（JVM 区分不同类的方式不仅仅根据类名，相同的类文件被不同的类加载器加载产生的是两个不同的类），
 * 也保证了 Java 的核心 API 不被篡改。如果没有使用双亲委派模型，而是每个类加载器加载自己的话就会出现一些问题，
 * 比如我们编写一个称为 java.lang.Object 类的话，那么程序运行的时候，系统就会出现多个不同的 Object 类。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/1
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
}

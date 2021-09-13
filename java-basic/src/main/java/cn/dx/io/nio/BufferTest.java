package cn.dx.io.nio;

import java.nio.CharBuffer;

/**
 * @ClassName BufferTest
 * @Description Buffer使用
 * @Author dongxian
 * @Date 20-5-15 下午12:05
 * @Version 1.0
 **/
public class BufferTest {
    public static void main(String[] args) {
        // 分配8个字符空间
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        //放入元素
        buffer.put("a");
        buffer.put("b");
        buffer.put("c");
        System.out.println("position after put three elements:" + buffer.position());

        // 调用flip()
        buffer.flip();
        System.out.println("after flip(),limit:" + buffer.limit());
        System.out.println("after flip(),position:" + buffer.position());

        //取出第一个元素
        System.out.println("first element:" + buffer.get());
        System.out.println("position:" + buffer.position());

        //调用clear()
        buffer.clear();
        System.out.println("after clear(),limit:" + buffer.limit());
        System.out.println("after clear(),position:" + buffer.position());

        System.out.println("third element:" + buffer.get(2));
        // 不会变化
        System.out.println("after absolute get(int), position:" + buffer.position());

        // ByteBuffer.allocateDirect() 成本比直接allocate()高，但读取效率高

    }
}

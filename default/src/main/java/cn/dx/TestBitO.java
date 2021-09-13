package cn.dx;

import java.beans.IntrospectionException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * cn.dx.TestBitO TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-22 下午4:10
 **/
public class TestBitO {
    public static void main(String[] args) throws IntrospectionException {
        int h = 100;
        System.out.println(Integer.toBinaryString(h));
        System.out.println((h >>> 5) + " " + Integer.toBinaryString(h >>> 5));
        System.out.println(Integer.toBinaryString(-1));

        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = mxBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + " " + threadInfo.getThreadName());
        }
    }
}

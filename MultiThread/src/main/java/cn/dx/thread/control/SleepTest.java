package cn.dx.thread.control;

import cn.hutool.core.date.DateUnit;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * @author dongxian
 * @version 1.0
 * @className SleepTest
 * @description 线程睡眠
 * @date 20-5-16 下午8:56
 **/
public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前时间："+ new Date());
            Thread.sleep(1000); //睡眠一秒
        }
    }
}

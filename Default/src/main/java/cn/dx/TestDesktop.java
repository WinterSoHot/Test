package cn.dx;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/14
 */
public class TestDesktop {
    public static void main(String[] args) throws IOException {
        if (Desktop.isDesktopSupported()) {
            // 获取当前平台桌面实例
            Desktop desktop = Desktop.getDesktop();
            // 使用默认浏览器打开链接
//            desktop.browse(URI.create("https://blog.csdn.net/xietansheng"));
//            desktop.browse(URI.create("file://E:"));
            desktop.browse(URI.create("file://E:"));
            // 打开指定文件/文件夹
//            desktop.open(new File("E:\\"));

        } else {
            System.out.println("当前平台不支持 Desktop");
        }
    }
}

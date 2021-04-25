package cn.dx.io.file;

import java.io.File;

/**
 * @ClassName FileDemo2
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-13 下午4:49
 * @Version 1.0
 **/
public class FileDemo2 {
    public static void main(String[] args) {
        // 文件过滤器
        File file = new File(".");
        String[] fileList = file.list((dir, name) -> name.endsWith(".xml"));
        for (String fileName : fileList) {
            System.out.println(fileName);
        }
    }
}

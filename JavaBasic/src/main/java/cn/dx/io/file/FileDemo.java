package cn.dx.io.file;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileDemo
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-13 下午4:45
 * @Version 1.0
 **/
public class FileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File(".");
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsoluteFile().getParent());

        File tempFile = File.createTempFile("aaa", ".txt", file);
        tempFile.deleteOnExit();

        File newFile = new File(System.currentTimeMillis() + "");
        System.out.println(String.format("当前文件是否存在 %s", newFile.exists()));

        newFile.mkdir();
        String[] fileList = file.list();
        System.out.println("当前路径下的所有文件");
        for (String fileName : fileList) {
            System.out.println(fileName);
        }

        File[] listRoots = File.listRoots();
        for (File root : listRoots) {
            System.out.println(root);
        }
    }
}

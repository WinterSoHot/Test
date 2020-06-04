package cn.dx.io.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author dongxian
 * @version 1.0
 * @className PathTest
 * @description NIO2 Java 1.7 Path,Paths,Files
 * @date 20-5-15 下午1:24
 **/
public class PathTest {
    public static void main(String[] args) {
        Path path = Paths.get(".");
        System.out.println("当前path包含的路径数量：" + path.getNameCount()); // 返回当前路径包含路径名的数量
        System.out.println("path的根路径：" + path.getRoot());
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println("当前absolutePath包含的路径数量：" + absolutePath.getNameCount());
        System.out.println("absolutePath的根路径：" + absolutePath.getRoot());
        System.out.println(absolutePath.getName(3));
        //多个String构建 Path
        Path path2 = Paths.get("/home", "dongxian", "github");
        System.out.println(path2);
    }
}

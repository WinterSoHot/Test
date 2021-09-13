package cn.dx.io.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author dongxian
 * @version 1.0
 * @className FileVisitorTest
 * @description FileVisitor访问文件树
 * @date 20-5-15 下午1:49
 **/
public class FileVisitorTest {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {
            /**
             * 访问文件的时候触发
             * @param path
             * @param basicFileAttributes
             * @return
             * @throws IOException
             */
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                System.out.println("正在访问：" + path + "文件");
                if (path.endsWith("IO_Operator.iml")) {
                    System.out.println("找到目标文件");
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                System.out.println("正在访问：" + path + "路径");
                return FileVisitResult.CONTINUE;
            }
        });
    }

}

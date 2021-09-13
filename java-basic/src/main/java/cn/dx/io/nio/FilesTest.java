package cn.dx.io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongxian
 * @version 1.0
 * @className FilesTest
 * @description Files 工具类的使用
 * @date 20-5-15 下午1:32
 **/
public class FilesTest {
    public static void main(String[] args) throws IOException {
        Files.copy(Paths.get("test"), new FileOutputStream("test2"));
        System.out.println("判断是否为隐藏文件：" + Files.isHidden(Paths.get("test")));
        // 读取文件所有行
        List<String> lines = Files.readAllLines(Paths.get("tr2"), StandardCharsets.UTF_8);
        System.out.println(lines);

        //判断文件大小
        System.out.println("文件大小为：" + Files.size(Paths.get("tr2")));

        List<String> poem = new ArrayList<>();
        poem.add("水晶潭底银鱼越");
        poem.add("清徐风中碧竿横");
        Files.write(Paths.get("tr3"), poem, StandardCharsets.UTF_8);

        // Java 8 使用StreamAPI列出当前子目录
        Files.list(Paths.get(".")).forEach(System.out::println);
        Files.lines(Paths.get("tr3")).forEach(System.out::println);

        FileStore fileStore = Files.getFileStore(Paths.get("/"));
        System.out.println("全部空间：" + fileStore.getTotalSpace() / 1024 / 1024 / 1024 + "G");
        System.out.println("可用空间" + fileStore.getUsableSpace());
    }
}

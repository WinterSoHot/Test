package cn.dx.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Date;
import java.util.List;

/**
 * @author dongxian
 * @version 1.0
 * @className FileAttributeViewTest
 * @description TODO
 * @date 20-5-15 下午2:02
 **/
public class FileAttributeViewTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("test");

        BasicFileAttributeView basicFileAttributeView = Files.
                getFileAttributeView(path,
                        BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
        System.out.println("创建时间：" + new Date(basicFileAttributes.creationTime().toMillis()));
        System.out.println("最后访问时间：" + new Date(basicFileAttributes.lastAccessTime().toMillis()));
        System.out.println("最后修改时间：" + new Date(basicFileAttributes.lastModifiedTime().toMillis()));
        System.out.println("文件大小：" + basicFileAttributes.size());

        // 访问文件从属信息
        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        //获取文件所属用户
        System.out.println(ownerAttributeView.getOwner());

        UserPrincipal user = FileSystems.getDefault()
                .getUserPrincipalLookupService()
                .lookupPrincipalByName("root");
        //修改用户
        //ownerAttributeView.setOwner(user);

        // 访问用户自定义属性
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        List<String> list = userDefinedFileAttributeView.list();
        list.forEach(s -> {
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(userDefinedFileAttributeView.size(s));
                userDefinedFileAttributeView.read(s, byteBuffer);
                byteBuffer.flip();
                String value = Charset.defaultCharset().decode(byteBuffer).toString();
                System.out.println(s + "--->" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //自定义属性
        userDefinedFileAttributeView.write("小李子", Charset.defaultCharset().encode("HHHa"));

        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        //设置隐藏只读
//        dosFileAttributeView.setHidden(true);
//        dosFileAttributeView.setReadOnly(true);

    }
}

package cn.dx.io.file;

import java.io.*;

/**
 * @ClassName InsertContentTest
 * @Description use RandomAccessFile class for inserting content into file.
 * @Author dongxian
 * @Date 20-5-14 上午11:27
 * @Version 1.0
 **/
public class InsertContentTest {

    public static void insertContent(String fileName, long pos, String content) throws IOException {
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        FileInputStream fis = new FileInputStream(tmp);
        FileOutputStream fos = new FileOutputStream(tmp);

        raf.seek(pos);
        byte[] buffer = new byte[1024];
        int hasRead = 0;
        while ((hasRead = raf.read(buffer)) > 0) {
            fos.write(buffer, 0, hasRead);
        }
        raf.seek(pos);
        raf.write(content.getBytes());
        while ((hasRead = fis.read(buffer)) > 0) {
            raf.write(buffer, 0, hasRead);
        }
        raf.close();
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        insertContent("insert", 45, "这是要加入的内容");
    }
}

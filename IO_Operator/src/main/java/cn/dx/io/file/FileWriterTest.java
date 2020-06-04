package cn.dx.io.file;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName FileWriterTest
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-13 下午5:44
 * @Version 1.0
 **/
public class FileWriterTest {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("tr2");
            fw.write("锦瑟-李商隐\t\n");
            fw.write("锦瑟无端五十弦，一弦一柱思华年。\t\n");
            fw.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃。\t\n");
            fw.write("沧海月明珠有泪，蓝田玉暖日生烟。\t\n");
            fw.write("此情可待成追忆，只是当时已惘然。\t\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

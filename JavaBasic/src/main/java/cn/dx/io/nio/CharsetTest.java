package cn.dx.io.nio;

import java.nio.charset.Charset;
import java.util.Properties;
import java.util.SortedMap;

/**
 * @author dongxian
 * @version 1.0
 * @className CharsetTest
 * @description TODO
 * @date 20-5-15 下午12:43
 **/
public class CharsetTest {
    public static void main(String[] args) {
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        availableCharsets.keySet().forEach((alias) -> {
            System.out.printf("%s--->%s%n", alias, availableCharsets.get(alias));
        });
        Properties properties = System.getProperties();
        System.out.println(properties.getProperty("file.encoding"));
    }
}

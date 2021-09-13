package cn.dx.io.redirect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @ClassName RedirectIn
 * @Description 重定向输入
 * @Author dongxian
 * @Date 20-5-14 上午9:55
 * @Version 1.0
 **/
public class RedirectIn {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("tr2");
        System.setIn(fis);

        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        while (scan.hasNext()) {
            System.out.println(scan.next());
        }
    }
}

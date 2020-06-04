package cn.dx;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 */
public class SpeechMatch {
    public static void main(String[] args) {
        String exe = "python";
        String command = "/home/dongxian/Projects/PycharmProjects/SpeechMatch/App.py";
        String num1 = "/home/dongxian/Projects/PycharmProjects/SpeechMatch/output.wav";
        String num2 = "/home/dongxian/Projects/PycharmProjects/SpeechMatch/output2.wav";
        String[] cmdArr = new String[]{exe, command, num1, num2};
        DataInputStream dis = null;
        InputStream is = null;
        try {
            Process process = Runtime.getRuntime().exec(cmdArr);
            is = process.getInputStream();
            dis = new DataInputStream(is);
            String str = dis.readLine();
            process.waitFor();
            System.out.println(str);
            dis.close();
            is.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {

            }
        }
    }
}

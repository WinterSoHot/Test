/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/29
 */
public class OutputColor {
    public static void main(String[] args) {
        /**
         * 格式：\033[*;*;*m
         * 颜色、背景颜色、样式都是用数字表示
         * 范围
         *     转义符之后的字符都会变成转义符所表示的样式
         *
         * 样式
         *
         *     0  空样式
         *
         *     1  粗体
         *
         *     4  下划线
         *
         *     7  反色
         *
         *     颜色1：
         *
         *     30  白色
         *
         *     31  红色
         *
         *     32  绿色
         *
         *     33  黄色
         *
         *     34  蓝色
         *
         *     35  紫色
         *
         *     36  浅蓝
         *
         *     37  灰色
         *
         *     背景颜色：
         *
         *     40-47 和颜色顺序相同
         *
         *     颜色2：
         *
         *     90-97  比颜色1更鲜艳一些
         */
        System.out.println("Hello,Akina!");
        System.out.println("\033[30;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[31;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[32;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[33;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[34;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[35;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[36;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[37;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[40;31;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[41;32;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[42;33;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[43;34;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[44;35;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[45;36;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[46;37;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[47;4m" + "Hello,Akina!" + "\033[0m");
    }
}

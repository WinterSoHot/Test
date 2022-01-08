package cn.dx.ambassador;

/**
 * @author gudongxian
 * @date 2022/1/8
 */
public class App {
    public static void main(String[] args) {
        Client host1 = new Client();
        Client host2 = new Client();
        host1.useService(12);
        host2.useService(73);
    }
}

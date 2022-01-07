package cn.dx.acyclicvisitor;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public class App {
    public static void main(String[] args) {
        ModemVisitor conUnix = new ConfigureForUnixVisitor();
        ModemVisitor conDos = new ConfigureForDosVisitor();

        Modem zoom = new Zoom();
        Modem hayes = new Hayes();
        hayes.accept(conUnix);
        zoom.accept(conUnix);
        hayes.accept(conDos);
        zoom.accept(conDos);
    }
}

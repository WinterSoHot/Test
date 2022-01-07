package cn.dx.acyclicvisitor;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public abstract class Modem {
    public abstract void accept(ModemVisitor visitor);
}

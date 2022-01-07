package cn.dx.acyclicvisitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@Slf4j
public class Hayes extends Modem {
    @Override
    public void accept(ModemVisitor visitor) {
        if (visitor instanceof HayesVisitor) {
            ((HayesVisitor) visitor).visit(this);
        } else {
            log.info("Only HayesVisitor is allowed to visit Hayes modem");
        }
    }

    @Override
    public String toString() {
        return "Hayes modem";
    }
}

package cn.dx.acyclicvisitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@Slf4j
public class Zoom extends Modem {
    @Override
    public void accept(ModemVisitor visitor) {
        if (visitor instanceof ZoomVisitor) {
            ((ZoomVisitor) visitor).visit(this);
        } else {
            log.info("Only ZoomVisitor is allowed to visit Zoom modem");
        }
    }

    @Override
    public String toString() {
        return "Zoom modem";
    }
}

package cn.dx.acyclicvisitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@Slf4j
public class ConfigureForDosVisitor implements AllModemVisitor {
    @Override
    public void visit(Hayes hayes) {
        log.info(hayes + " used with Dos configurator.");
    }

    @Override
    public void visit(Zoom zoom) {
        log.info(zoom + " used with Dos configurator.");
    }
}

package cn.dx.acyclicvisitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@Slf4j
public class ConfigureForUnixVisitor implements ZoomVisitor {

    @Override
    public void visit(Zoom zoom) {
        log.info(zoom + " used with Unix configurator");
    }
}

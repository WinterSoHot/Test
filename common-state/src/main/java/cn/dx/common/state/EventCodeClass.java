package cn.dx.common.state;

import java.lang.annotation.*;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventCodeClass {
    Class<? extends EventCode> value();
}

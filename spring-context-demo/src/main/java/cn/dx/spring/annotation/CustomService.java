package cn.dx.spring.annotation;

import java.lang.annotation.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomService {
}

package cn.dx.spring.annotation;

import cn.dx.spring.CustomScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomScannerRegistrar.class)
@Documented
public @interface CustomScan {

    String[] basePackage();

}


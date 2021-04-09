package cn.dx.redis.cotroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/9
 */
@RestController
@Slf4j
public class StopController implements ApplicationContextAware {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @PostMapping("/shutdown")
    public void shutdown() throws IOException {
        ConfigurableApplicationContext cyx = (ConfigurableApplicationContext) context;
        cyx.close();
    }

}

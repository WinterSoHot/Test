package cn.dx.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootDemoApplication.class);
        app.addListeners(new ApplicationListener<ApplicationReadyEvent>() {

            @Override
            public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
                log.info("SpringBoot 程序启动成功");
            }
        });
        ConfigurableApplicationContext context = app.run(args);

        int length = context.getBeanDefinitionNames().length;
        log.trace("Spring boot启动初始化了 {} 个 Bean", length);
        log.debug("Spring boot启动初始化了 {} 个 Bean", length);
        log.info("Spring boot启动初始化了 {} 个 Bean", length);
        log.warn("Spring boot启动初始化了 {} 个 Bean", length);
        log.error("Spring boot启动初始化了 {} 个 Bean", length);
        try {
            int i = 0;
            int j = 1 / i;
        } catch (Exception e) {
            log.error("【SpringBootDemoLogbackApplication】启动异常：", e);
        }
    }
}

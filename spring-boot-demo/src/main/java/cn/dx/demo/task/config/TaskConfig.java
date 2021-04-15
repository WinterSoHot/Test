package cn.dx.demo.task.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/15
 */
@Configuration
@EnableScheduling
public class TaskConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(executor());
    }

    @Bean
    public Executor executor() {
        return new ScheduledThreadPoolExecutor(20, new BasicThreadFactory.Builder().namingPattern("Job-Thread-%d").build());
    }
}

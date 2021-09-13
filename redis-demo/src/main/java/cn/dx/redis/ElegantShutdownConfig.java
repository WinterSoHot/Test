package cn.dx.redis;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/9
 */
@Slf4j
public class ElegantShutdownConfig implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;
    private final int waitTime = 10;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        connector.pause();
        Executor executor = connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
            threadPoolExecutor.shutdown();
            if (!threadPoolExecutor.awaitTermination(waitTime, TimeUnit.SECONDS)) {
                log.info("请尝试暴力关闭");
            }
        }
    }
}

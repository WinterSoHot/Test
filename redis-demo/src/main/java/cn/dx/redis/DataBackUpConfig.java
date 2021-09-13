package cn.dx.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/9
 */
@Configuration
@Slf4j
public class DataBackUpConfig {

    @PreDestroy
    public void dataBackup() {
        log.info("正在数据备份");
    }
}

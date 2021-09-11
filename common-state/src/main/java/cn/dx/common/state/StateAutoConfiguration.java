package cn.dx.common.state;

import cn.dx.common.state.impl.StateFileParserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "state.enable", havingValue = "true")
public class StateAutoConfiguration {
    @PostConstruct
    public void init(){
        log.warn("--common state init--");
    }
    @Bean
    public StateFactory stateFactory(){
        return new StateFactory();
    }
    @Bean
    public StateProperties stateProperties(){
        return new StateProperties();
    }
    @Bean
    public StateFileParser stateFileParser(){
        return new StateFileParserImpl();
    }
}

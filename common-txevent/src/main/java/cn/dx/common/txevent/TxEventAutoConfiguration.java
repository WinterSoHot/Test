package cn.dx.common.txevent;

import cn.dx.common.txevent.spi.TxEventProcessSpi;
import cn.dx.common.txevent.spi.impl.DefaultTxEventProcessSpi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
@Configuration
@ComponentScan(basePackages = "cn.dx.common.txevent")
public class TxEventAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TxEventProcessSpi txEventProcessSpi() {
        return new DefaultTxEventProcessSpi();
    }

}

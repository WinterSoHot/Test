package cn.dx.demo.property.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 开发者信息
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/15
 */
@Data
@Component
@ConfigurationProperties(prefix = "developer")
public class DeveloperProperty {
    private String name;
    private String phoneNumber;
}

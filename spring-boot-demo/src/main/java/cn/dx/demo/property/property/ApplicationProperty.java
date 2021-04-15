package cn.dx.demo.property.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 项目配置
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/15
 */
@Data
@Component
public class ApplicationProperty {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
}

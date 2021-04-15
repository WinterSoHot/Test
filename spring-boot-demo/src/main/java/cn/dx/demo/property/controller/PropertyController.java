package cn.dx.demo.property.controller;

import cn.dx.demo.property.property.ApplicationProperty;
import cn.dx.demo.property.property.DeveloperProperty;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/15
 */
@RequestMapping("/property")
@RestController
@AllArgsConstructor
public class PropertyController {
    private ApplicationProperty applicationProperty;
    private DeveloperProperty developerProperty;

    @GetMapping("/app")
    public ApplicationProperty applicationProperty() {
        return applicationProperty;
    }

    @GetMapping("developer")
    public DeveloperProperty developerProperty() {
        return developerProperty;
    }
}

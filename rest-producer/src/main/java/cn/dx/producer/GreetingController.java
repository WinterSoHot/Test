package cn.dx.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/30
 */
public interface GreetingController {
    @GetMapping("/greeting/{username}")
    String greeting(@PathVariable("username") String username);
}

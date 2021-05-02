package cn.dx.producer;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/30
 */
@RestController
public class GreetingControllerImpl implements GreetingController {
    @Override
    public String greeting(String username) {
        return String.format("Hello, %s\n", username);
    }
}

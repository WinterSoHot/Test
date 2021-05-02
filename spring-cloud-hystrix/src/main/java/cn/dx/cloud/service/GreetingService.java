package cn.dx.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/1
 */
@Service
@AllArgsConstructor
public class GreetingService {
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting(String username) {
        return restTemplate.getForObject("http://localhost:9090/greeting/{username}", String.class, username);
    }

    private String defaultGreeting(String username) {
        return "Hello User!";
    }
}

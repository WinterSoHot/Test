package cn.dx.redis.cotroller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/3
 */
@RestController
public class IndexController {

    Redisson redisson;

    StringRedisTemplate redisTemplate;

    public IndexController(Redisson redisson, StringRedisTemplate redisTemplate) {
        this.redisson = redisson;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = "lockKey";
        String clientId = UUID.randomUUID().toString();
        RLock lock = redisson.getLock(lockKey);
        try {
           /* Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            if (!result) {
                return "error_code";
            }*/
            lock.lock();
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            /*if (clientId.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }*/
            lock.unlock();
        }

        return "end";
    }
}

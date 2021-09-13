package cn.dx.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/21
 */
public class JedisDemo {
    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("127.0.0.1")) {
            String result = jedis.mset("article:1:title", "Redis 使用",
                    "article:1:content", "Redis的使用技巧",
                    "article:1:author", "谷东先"
            );
            System.out.println("MSET Result: " + result);
            List<String> rets = jedis.mget("article:1:title", "article:1:content");
            System.out.println(rets);
        }
    }
}

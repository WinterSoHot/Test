package cn.dx.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/19
 */
public class ConcurrenceMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("1231", 1231);
    }
}

package cn.dx.netty.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/27
 */
public class JsonUtil {
    static GsonBuilder gb = new GsonBuilder();

    static {
        gb.disableHtmlEscaping();
    }

    /**
     * 序列化
     *
     * @param obj 对象
     * @return json
     */
    public static String stringPojoToJson(Object obj) {
        return gb.create().toJson(obj);
    }

    /**
     * 反序列化
     *
     * @param json  json串
     * @param clazz 类
     * @param <T>   类泛型
     * @return T实例
     */
    public static <T> T jsonToPojo(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }
}

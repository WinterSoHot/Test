package cn.dx.netty.util;

import lombok.Data;
import lombok.ToString;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/27
 */
@Data
@ToString
public class JsonMsg {
    private int id;
    private String content;

    public String convertToJson() {
        return JsonUtil.stringPojoToJson(this);
    }

    public static JsonMsg parseFromJson(String json) {
        return JsonUtil.jsonToPojo(json, JsonMsg.class);
    }
}

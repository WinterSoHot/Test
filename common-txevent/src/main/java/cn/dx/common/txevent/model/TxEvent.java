package cn.dx.common.txevent.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import org.springframework.util.Assert;

import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import java.util.Map;

/**
 * 事务事件类型
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public class TxEvent<Body> {
    private Long userId;
    private String eventId;
    private String eventType;
    private Body eventBody;
    private long delayMilliSeconds;
    private Map<String, String> properties = new HashMap<>();

    public TxEvent addProperties(String key, String value) {
        Assert.notNull(key, "key is null");
        Assert.notNull(value, "value is null");
        properties.put(key, value);
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Body getEventBody() {
        return eventBody;
    }

    public void setEventBody(Body eventBody) {
        this.eventBody = eventBody;
    }

    public long getDelayMilliSeconds() {
        return delayMilliSeconds;
    }

    public void setDelayMilliSeconds(long delayMilliSeconds) {
        this.delayMilliSeconds = delayMilliSeconds;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.IgnoreNonFieldGetter);
    }
}

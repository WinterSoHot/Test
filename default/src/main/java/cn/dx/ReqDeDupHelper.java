package cn.dx;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 请求去重帮助类
 *
 * @author dongxian
 * @version 0.1
 * @date 20-11-21
 **/
public class ReqDeDupHelper {

    /**
     * @param reqJSON     请求的参数，这里通常是JSON
     * @param excludeKeys 请求参数里面要去除哪些字段再求摘要
     * @return 去除参数的MD5摘要
     */
    public String dedupParamMD5(final String reqJSON, String... excludeKeys) {
        String decreptParam = reqJSON;
        TreeMap paramTreeMap = JSON.parseObject(reqJSON, TreeMap.class);
        if (excludeKeys != null) {
            List<String> deDupExcludeKeys = Arrays.asList(excludeKeys);
            if (!deDupExcludeKeys.isEmpty()) {
                for (String deDupExcludeKey : deDupExcludeKeys) {
                    paramTreeMap.remove(deDupExcludeKey);
                }
            }
        }
        String paramTreeMapJSON = JSON.toJSONString(paramTreeMap);
        String md5deDupParam = jdkMD5(paramTreeMapJSON);
        System.out.printf("md5deDupParam = %s, excludeKeys = %s %s", md5deDupParam, Arrays.deepToString(excludeKeys), paramTreeMapJSON);
        return md5deDupParam;
    }

    private String jdkMD5(String src) {
        String res = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] digest = md5.digest(src.getBytes());
            res = DatatypeConverter.printHexBinary(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        //两个请求一样，但是请求时间差一秒
        String req = "{\n" +
                "\"requestTime\" :\"20190101120001\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        String req2 = "{\n" +
                "\"requestTime\" :\"20190101120002\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        //全参数比对，所以两个参数MD5不同
        String dedupMD5 = new ReqDeDupHelper().dedupParamMD5(req);
        String dedupMD52 = new ReqDeDupHelper().dedupParamMD5(req2);
        System.out.println("req1MD5 = " + dedupMD5 + " , req2MD5=" + dedupMD52);

        //去除时间参数比对，MD5相同
        String dedupMD53 = new ReqDeDupHelper().dedupParamMD5(req, "requestTime");
        String dedupMD54 = new ReqDeDupHelper().dedupParamMD5(req2, "requestTime");
        System.out.println("req1MD5 = " + dedupMD53 + " , req2MD5=" + dedupMD54);
        /*
        String userId= "12345678";//用户
        String method = "pay";//接口名
        String dedupMD5 = new ReqDedupHelper().dedupParamMD5(req,"requestTime");//计算请求参数摘要，其中剔除里面请求时间的干扰
        String KEY = "dedup:U=" + userId + "M=" + method + "P=" + dedupMD5;

        long expireTime =  1000;// 1000毫秒过期，1000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;

// NOTE:直接SETNX不支持带过期时间，所以设置+过期不是原子操作，极端情况下可能设置了就不过期了，后面相同请求可能会误以为需要去重，所以这里使用底层API，保证SETNX+过期时间是原子操作
        Boolean firstSet = stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(KEY.getBytes(), val.getBytes(), Expiration.milliseconds(expireTime),
                RedisStringCommands.SetOption.SET_IF_ABSENT));

        final boolean isConsiderDup;
        if (firstSet != null && firstSet) {
            isConsiderDup = false;
        } else {
            isConsiderDup = true;
        }
        */
    }
}

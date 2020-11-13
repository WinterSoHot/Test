package cn.dx.dto;

import cn.dx.UDPUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/7
 */
public class HeatMapTest {
    public static void main(String[] args) throws JsonProcessingException {
        CommonMessage<List<HeatMap>> commonMessage = new CommonMessage<>();
        commonMessage.setId(1);
        commonMessage.setType(CommonMessage.HEATMAP);

        List<HeatMap> heatMaps = new ArrayList<>();

        HeatMap heatMap = new HeatMap(10, "北京市");
        List<HeatMap.Info> info = new ArrayList<>();
        info.add(new HeatMap.Info("12313", "这是一段信息"));
        info.add(new HeatMap.Info("12313", "这是一段信息"));
        info.add(new HeatMap.Info("12313", "这是一段信息"));
        heatMap.setInfo(info);
        heatMaps.add(heatMap);

        commonMessage.setData(heatMaps);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(commonMessage);
        System.out.println(json);
        String resp = UDPUtil.sendDataByUDP("127.0.0.1", 6666, json);

        if (resp.isEmpty()) {
            System.out.println("响应为空");
        } else {
            System.out.println(resp);
        }
    }
}

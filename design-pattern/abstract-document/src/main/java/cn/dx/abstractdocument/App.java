package cn.dx.abstractdocument;

import cn.dx.abstractdocument.domain.Car;
import cn.dx.abstractdocument.domain.enums.Property;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("构造部件和汽车");

        Map<String, Object> wheelProperties = new HashMap<>();
        wheelProperties.put(Property.TYPE.toString(), "wheel");
        wheelProperties.put(Property.MODEL.toString(), "15C");
        wheelProperties.put(Property.PRICE.toString(), 100L);

        Map<String, Object> doorProperties = new HashMap<>();
        doorProperties.put(Property.TYPE.toString(), "door");
        doorProperties.put(Property.MODEL.toString(), "Lambo");
        doorProperties.put(Property.PRICE.toString(), 300L);

        Map<String, Object> carProperties = new HashMap<>();
        carProperties.put(Property.PRICE.toString(), 10000L);
        carProperties.put(Property.MODEL.toString(), "300SL");
        List<Map<String, Object>> parts = new ArrayList<>();
        parts.add(wheelProperties);
        parts.add(doorProperties);
        carProperties.put(Property.PARTS.toString(), parts);

        Car car = new Car(carProperties);

        log.info("汽车组成");
        log.info("-> model: {}", car.getModel());
        log.info("-> price: {}", car.getPrice());
        log.info("-> parts");
        car.getParts().forEach(e -> log.info("\t{}/{}/{}",
                e.getType().orElse(null),
                e.getModel().orElse(null),
                e.getPrice().orElse(null)));
    }
}

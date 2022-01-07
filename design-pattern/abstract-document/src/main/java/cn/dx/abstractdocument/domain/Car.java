package cn.dx.abstractdocument.domain;

import cn.dx.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public class Car extends AbstractDocument implements HasPrice, HasModel, HasParts {
    public Car(Map<String, Object> properties) {
        super(properties);
    }
}

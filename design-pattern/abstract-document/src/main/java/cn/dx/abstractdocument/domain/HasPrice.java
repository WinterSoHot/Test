package cn.dx.abstractdocument.domain;

import cn.dx.abstractdocument.Document;
import cn.dx.abstractdocument.domain.enums.Property;

import java.util.Optional;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public interface HasPrice extends Document {
    default Optional<Number> getPrice() {
        return Optional.ofNullable((Number) get(Property.PRICE.toString()));
    }
}

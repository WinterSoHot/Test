package cn.dx.abstractdocument.domain;

import cn.dx.abstractdocument.Document;
import cn.dx.abstractdocument.domain.enums.Property;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public interface HasType extends Document {
    default Optional<String> getType() {
        return Optional.ofNullable((String) get(Property.TYPE.toString()));
    }
}

package cn.dx.abstractdocument.domain;

import cn.dx.abstractdocument.Document;
import cn.dx.abstractdocument.domain.enums.Property;

import java.util.Optional;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public interface HasModel extends Document {
    default Optional<String> getModel() {
        return Optional.ofNullable((String) get(Property.MODEL.toString()));
    }
}

package cn.dx.abstractdocument.domain;

import cn.dx.abstractdocument.Document;
import cn.dx.abstractdocument.domain.enums.Property;

import java.util.stream.Stream;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public interface HasParts extends Document {
    default Stream<Part> getParts() {
        return children(Property.PARTS.toString(), Part::new);
    }
}

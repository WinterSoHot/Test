package cn.dx.abstractdocument.domain;

import cn.dx.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public class Part extends AbstractDocument implements HasPrice, HasType, HasModel {
    protected Part(Map<String, Object> properties) {
        super(properties);
    }
}

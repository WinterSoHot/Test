package cn.dx.abstractdocument;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public abstract class AbstractDocument implements Document {
    private final Map<String, Object> properties;

    protected AbstractDocument(Map<String, Object> properties) {
        Objects.requireNonNull(properties, "properties map is required");
        this.properties = properties;
    }

    @Override
    public void put(String key, Object value) {
        properties.put(key, value);
    }

    @Override
    public Object get(String key) {
        return properties.get(key);
    }

    @Override
    public <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor) {
        return Stream.of(get(key)).filter(Objects::nonNull).map(el -> (List<Map<String, Object>>) el).flatMap(Collection::stream).map(constructor);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("[");
        properties.forEach((key, value) -> builder.append("[").append(key).append(" : ").append(value).append("]"));
        builder.append("]");
        return builder.toString();
    }
}

package cn.dx.common.state;


import cn.dx.common.state.exception.StateNotExistException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;


/**
 * @author gudongxian
 * @date 2021/7/22
 */
@Slf4j
public class StateFactory {
    private static final Map<String,State> STATE_MAP = Maps.newHashMap();

    @Autowired
    private StateProperties properties;
    @Autowired
    private StateFileParser stateFileParser;
    private ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    public static State getState(Class<? extends Status> statusClass){
        return Optional.ofNullable(STATE_MAP.get(statusClass.getName())).orElseThrow(
                () -> new StateNotExistException(statusClass.getName())
        );
    }

    @PostConstruct
    public void init() throws Exception{
        log.warn("-- state init start --");
        for (int i = 0; i < properties.getFilepaths().size(); i++) {
            String filePath = properties.getFilepaths().get(i);
            String packageName = properties.getPackagenames().get(i);
            Resource[] resources = resolver.getResources(filePath + "/*.puml");
            for (Resource resource : resources) {
                String className = packageName + "." + resource.getFilename().substring(0, resource.getFilename().lastIndexOf("."));
                Class<? extends Status> statusClass = (Class<? extends Status>) Class.forName(className);
                State state = stateFileParser.parserFile(statusClass, resource);
                STATE_MAP.put(statusClass.getName(), state);
                log.warn(state.toString());
            }
        }
        log.warn("-- state init end --");
    }
}

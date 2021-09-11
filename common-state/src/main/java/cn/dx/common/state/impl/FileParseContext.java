package cn.dx.common.state.impl;

import cn.dx.common.state.Code;
import cn.dx.common.state.EventCodeClass;
import cn.dx.common.state.Status;
import cn.dx.common.state.exception.StateFileParseException;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class FileParseContext {
    private StateImpl state;
    private Set<String> statusCodes;
    private Set<String> eventCodes;

    public static FileParseContext create(Class<? extends Status> statusClass) {
        FileParseContext fileParseContext = new FileParseContext();
        EventCodeClass eventCodeClass = statusClass.getAnnotation(EventCodeClass.class);
        check(statusClass, eventCodeClass);
        fileParseContext.setState(new StateImpl());
        fileParseContext.setStatusCodes(getEnumAllCode(statusClass));
        fileParseContext.setEventCodes(getEnumAllCode(eventCodeClass.value()));
        return fileParseContext;
    }

    private static Set<String> getEnumAllCode(Class clazz) {
        try {
            Method method = clazz.getMethod("values");
            Code[] codes = (Code[]) method.invoke(null);
            return Arrays.stream(codes).map(Code::getCode).collect(Collectors.toSet());
        } catch (Exception e) {
            throw new StateFileParseException("get enum all code error:" + clazz, e);
        }
    }

    private static void check(Class<? extends Status> statusClass, EventCodeClass eventCodeClass) {
        Assert.notNull(eventCodeClass, statusClass + " not find EventCodeClass annotation");
        Assert.isTrue(statusClass.isEnum(), "statusClass is not Enum");
        Assert.isTrue(eventCodeClass.value().isEnum(), " EventCodeClass is not Enum");
    }

    public StateImpl getState() {
        return state;
    }

    public void setState(StateImpl state) {
        this.state = state;
    }

    public Set<String> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(Set<String> statusCodes) {
        this.statusCodes = statusCodes;
    }

    public Set<String> getEventCodes() {
        return eventCodes;
    }

    public void setEventCodes(Set<String> eventCodes) {
        this.eventCodes = eventCodes;
    }
}

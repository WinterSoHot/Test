package cn.dx.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gudongxian
 * @date 2022/1/14
 */
@Data
public class InvokeProtocol implements Serializable {
    private String className;
    private String methodName;
    private Class<?>[] params;
    private Object[] values;
}

package cn.dx.drools;

import lombok.Data;

/**
 * 定义结果输出
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/26
 */
@Data
public class Result {
    private String code;
    private String info;

    public void setResult(String code, String info) {
        this.code = code;
        this.info = info;
    }
}

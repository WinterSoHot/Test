package cn.dx.drools;

import lombok.Data;

/**
 * Fact 定义决策属性： 对应Fact
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/26
 */
@Data
public class Policy {

    /**
     * 性别；男、女
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 单身；是/否
     */
    private Boolean userSingle;

    /**
     * 结婚；是/否
     */
    private Boolean userMarry;

    /**
     * 育儿；是/否
     */
    private Boolean userParenting;

}

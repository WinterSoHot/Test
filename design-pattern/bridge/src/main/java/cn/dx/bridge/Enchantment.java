package cn.dx.bridge;

/**
 * @author gudongxian
 * @date 2022/2/9
 */
public interface Enchantment {

    /**
     * 生效
     */
    void onActivate();

    /**
     * 使用
     */
    void apply();

    /**
     * 失效
     */
    void onDeactivate();
}

package cn.dx.bridge;

/**
 * @author gudongxian
 * @date 2022/2/9
 */
public interface Weapon {
    /**
     * 挥舞
     */
    void wield();

    /**
     * 摆动
     */
    void swing();

    /**
     * 放置
     */
    void unWield();

    Enchantment getEnchantment();
}

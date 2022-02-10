package cn.dx.bridge;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 剑
 *
 * @author gudongxian
 * @date 2022/2/9
 */
@Slf4j
@AllArgsConstructor
public class Sword implements Weapon {
    private final Enchantment enchantment;

    @Override
    public void wield() {
        LOGGER.info("The sword is wielded.");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        LOGGER.info("The sword is swung.");
        enchantment.apply();
    }

    @Override
    public void unWield() {
        LOGGER.info("The sword is unwielded.");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}

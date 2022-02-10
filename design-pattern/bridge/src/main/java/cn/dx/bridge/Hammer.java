package cn.dx.bridge;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 锤子
 *
 * @author gudongxian
 * @date 2022/2/9
 */
@Slf4j
@AllArgsConstructor
public class Hammer implements Weapon {

    private final Enchantment enchantment;

    @Override
    public void wield() {
        LOGGER.info("The hammer is wielded.");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        LOGGER.info("The hammer is swung.");
        enchantment.apply();
    }

    @Override
    public void unWield() {
        LOGGER.info("The hammer is unwielded.");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}

package cn.dx.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 噬魂魔法
 *
 * @author gudongxian
 * @date 2022/2/10
 */
@Slf4j
public class SoulEatingEnchantment implements Enchantment {
    @Override
    public void onActivate() {
        LOGGER.info("The item spreads bloodlust.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item eats the soul of enemies.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("Bloodlust slowly disappears.");
    }
}

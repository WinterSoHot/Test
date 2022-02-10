package cn.dx.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gudongxian
 * @date 2022/2/10
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        LOGGER.info("The knight receives an enchanted sword.");
        Weapon enchantedSword = new Sword(new SoulEatingEnchantment());
        enchantedSword.wield();
        enchantedSword.swing();
        enchantedSword.unWield();

        LOGGER.info("The valkyrie receives an enchanted hammer.");
        Weapon hammer = new Hammer(new FlyingEnchantment());
        hammer.wield();
        hammer.swing();
        hammer.unWield();
    }
}

package cn.dx.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 飞翔魔法
 * @author gudongxian
 * @date 2022/2/9
 */
@Slf4j
public class FlyingEnchantment implements Enchantment {

    @Override
    public void onActivate() {
        LOGGER.info("这个物品开始发出微弱的光芒");
    }

    @Override
    public void apply() {
        LOGGER.info("该物品会飞起来并击中敌人，最后回到主人手中。");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("该物品的光芒逐渐消失");
    }
}

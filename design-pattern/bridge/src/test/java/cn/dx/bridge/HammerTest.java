package cn.dx.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * @author gudongxian
 * @date 2022/2/10
 */
class HammerTest extends WeaponTest {
    @Test
    void testHammer() {
        final Weapon hammer = spy(new Hammer(mock(FlyingEnchantment.class)));
        testBasicWeaponActions(hammer);
    }

}
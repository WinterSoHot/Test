package cn.dx.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * @author gudongxian
 * @date 2022/2/10
 */
class SwordTest extends WeaponTest {

    /**
     * Invoke all possible actions on the weapon and check if the actions are executed on the actual
     * underlying weapon implementation.
     */
    @Test
    void testSword() {
        final Weapon sword = spy(new Sword(mock(FlyingEnchantment.class)));
        testBasicWeaponActions(sword);
    }

}
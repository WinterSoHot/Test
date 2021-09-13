package cn.dx.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MostStonesRemovedTest {

    @Test
    @DisplayName("移除石头测试用例")
    void removeStones() {
        MostStonesRemoved msr = new MostStonesRemoved();
        int[][] stones = new int[][]{
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{1, 0},
                new int[]{1, 2},
                new int[]{2, 1},
                new int[]{2, 2}
        };
        Assertions.assertEquals(5, msr.removeStones(stones));
        Assertions.assertEquals(3, msr.removeStones(new int[][]{
                new int[]{0, 0},
                new int[]{0, 2},
                new int[]{1, 1},
                new int[]{2, 0},
                new int[]{2, 2}
        }));
    }
}
package cn.dx.leetcode;

/**
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-place-flowers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/1
 **/
public class PlaceFlower {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        int pre = -1;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                if (pre < 0) {
                    count += i / 2;
                } else {
                    count += (i - pre - 2) / 2;
                }
                pre = i;
            }
        }
        if (pre < 0) {
            count += (len + 1) / 2;
        } else {
            count += (len - pre - 1) / 2;
        }
        return count >= n;
    }
}

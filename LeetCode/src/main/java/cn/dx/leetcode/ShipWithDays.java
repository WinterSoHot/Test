package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/26
 */
public class ShipWithDays {
    public int shipWithinDays(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();

        while (left < right) {
            int mid = (left + right) >> 1;
            int curDays = 1;
            int cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    curDays++;
                    cur = 0;
                }
                cur += weight;
            }
            if (curDays <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

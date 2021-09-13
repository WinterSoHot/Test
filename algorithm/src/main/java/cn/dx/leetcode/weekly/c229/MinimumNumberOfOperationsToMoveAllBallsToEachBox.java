package cn.dx.leetcode.weekly.c229;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/21
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public int[] minOperations(String boxes) {
        int len = boxes.length();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = 0; j < len; j++) {
                if (boxes.charAt(j) == '1') {
                    count += Math.abs(i - j);
                }
            }
            ans[i] = count;
        }
        return ans;
    }
}

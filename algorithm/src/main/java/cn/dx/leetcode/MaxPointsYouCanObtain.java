package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 1423. 可获得的最大点数
 * <p>
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * <p>
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * <p>
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * <p>
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/6
 **/
public class MaxPointsYouCanObtain {
    public int maxScore(int[] cardPoints, int k) {
        int ans = 0;
        int sum = Arrays.stream(cardPoints).sum();
        int len = cardPoints.length;
        int remove = len - k;
        int all = 0;
        for (int i = 0; i < remove; i++) {
            all += cardPoints[i];
        }
        ans = Math.max(ans, sum - all);
        for (int i = remove; i < len; i++) {
            all -= cardPoints[i - remove];
            all += cardPoints[i];
            ans = Math.max(ans, sum - all);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxPointsYouCanObtain m = new MaxPointsYouCanObtain();
        System.out.println(m.maxScore(new int[]{96, 90, 41, 82, 39, 74, 64, 50, 30}, 8));
        System.out.println(m.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println(m.maxScore(new int[]{2, 2, 2}, 2));
        System.out.println(m.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
        System.out.println(m.maxScore(new int[]{1, 1000, 1}, 1));
        System.out.println(m.maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
    }
}

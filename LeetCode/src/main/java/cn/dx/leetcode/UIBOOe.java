package cn.dx.leetcode;

/**
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/UlBDOe
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 * <p>
 * 方法：动态规划
 * <p>
 * 叶子为 [ 红 黄 红] 可以代表三种状态
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/1
 */
public class UIBOOe {

    public int minimumOperations(String leaves) {
        int n = leaves.length();
        int dp[][] = new int[n][3];
        dp[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < leaves.length(); i++) {

            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            int isRed = leaves.charAt(i) == 'r' ? 1 : 0;

            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;
            if (i > 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }

        return dp[n - 1][2];
    }


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("12345");
        sb.replace(0, 1, "2");
//        System.out.println(sb.toString());
        UIBOOe uiboOe = new UIBOOe();
        System.out.println(uiboOe.minimumOperations("ryyryyyrryyyyyryyyrrryyyryryyyyryyrrryryyyryrryrrrryyyrrrrryryyrrrrryyyryyryrryryyryyyyryyrryrryryy"));
    }
}

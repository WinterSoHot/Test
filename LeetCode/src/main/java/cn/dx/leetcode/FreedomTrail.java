package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * <p>
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * <p>
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * <p>
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * <p>
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 * 示例：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/freedom-trail
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 难度：困难
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/12
 */
public class FreedomTrail {
    /**
     * 56
     *
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps(String ring, String key) {
        int ret = 0;
        int n = key.length();
        int cl = ring.length();
        int init = 0;
        char[] chars = ring.toCharArray();

        for (int i = 0; i < n; i++) {
            char ch = key.charAt(i);
            int minStep = Integer.MAX_VALUE;
            int nextInit = init;
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                if (c == ch) {
                    int step = Math.abs(j - init);
                    step = Math.min(step, cl - step);
                    //bug 如果两个step一样，无法确定选那个
                    if (step < minStep) {
                        minStep = step;
                        nextInit = j;
                    }
                }
            }
            ret += minStep + 1;
            init = nextInit;
        }
        return ret;
    }

    /**
     * 官方解法
     * 动态规划
     *
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps2(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        // 保存26字母对应字符的位置
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        // 表示 key 第 m 个字符和 ring 第 n 字符对应的步数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        // 初始化，初始步数 base case
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    // 上一步 + 当前步数min（正向，反向） + 1
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();

    }


    public static void main(String[] args) {
        FreedomTrail ft = new FreedomTrail();
        ft.findRotateSteps("godding", "godding");
    }
}

package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 455. 分发饼干
 * <p>
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/assign-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * level: easy
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/25
 **/
public class AssignCookie {
    /**
     * 排序+贪心
     *
     * @param g 胃口
     * @param s 饼干
     * @return 满足的数量
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int n = s.length;
        int res = 0;
        for (int i : g) {
            if (start < n) {
                while (start < n && s[start] < i) {
                    start++;
                }
                if (start < n) {
                    start++;
                    res++;
                }
            } else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        AssignCookie ac = new AssignCookie();
        System.out.println(ac.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(ac.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
        System.out.println(ac.findContentChildren(new int[]{1, 2, 3}, new int[]{3}));
    }
}

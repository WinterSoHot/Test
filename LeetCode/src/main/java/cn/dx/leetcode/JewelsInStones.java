package cn.dx.leetcode;

/**
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * <p>
 * https://leetcode-cn.com/problems/jewels-and-stones/
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/2
 */
public class JewelsInStones {
    public int numJewelsInStones(String J, String S) {
        char[] chars = S.toCharArray();
        int res = 0;
        for (Character stone : chars) {
            if (J.indexOf(stone) != -1) {
                res++;
            }
        }
        return res;
    }
}

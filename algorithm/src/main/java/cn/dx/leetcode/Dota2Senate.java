package cn.dx.leetcode;

/**
 * 649. Dota2 参议院
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 * <p>
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
 * <p>
 * 禁止一名参议员的权利：
 * <p>
 * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 * <p>
 * 宣布胜利：
 * <p>
 *           如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
 * <p>
 *  
 * <p>
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * <p>
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * <p>
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dota2-senate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2020/12/11
 */
public class Dota2Senate {
    public String predictPartyVictory(String senate) {
        char[] chars = senate.toCharArray();
        // 记录双方当前剩余的个数
        int radiant = 0;
        int dire = 0;
        for (char ch : chars) {
            if (ch == 'R') {
                radiant++;
            } else {
                dire++;
            }
        }
        // 记录待删除的个数
        int deleteDire = 0;
        int deleteRadiant = 0;
        while (radiant > 0 && dire > 0) {
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                // 如果当前位置已经删除
                if (ch == '0') {
                    continue;
                }
                if (ch == 'R') {
                    // 如果待删除为0，则当前位置可以删除另一方
                    if (deleteRadiant == 0) {
                        // 增加另一方待删除
                        deleteDire++;
                        // 总数量减少
                        dire--;
                    } else {
                        // 存在待删除的，则当前位置删除，待删除个数减一
                        deleteRadiant--;
                        chars[i] = '0';
                    }
                }
                if (ch == 'D') {
                    // 和上方同理
                    if (deleteDire == 0) {
                        deleteRadiant++;
                        radiant--;
                    } else {
                        deleteDire--;
                        chars[i] = '0';
                    }
                }
            }
        }
        return radiant > dire ? "Radiant" : "Dire";
    }

    public static void main(String[] args) {
        Dota2Senate ds = new Dota2Senate();
        ds.predictPartyVictory("RDR");
    }
}

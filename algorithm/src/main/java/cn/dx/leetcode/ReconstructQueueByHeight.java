package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对 (h, k) 表示，其中 h 是这个人的身高，k 是应该排在这个人前面且身高大于或等于 h 的人数。 例如：[5,2] 表示前面应该有 2 个身高大于等于 5 的人，而 [5,0] 表示前面不应该存在身高大于等于 5 的人。
 * <p>
 * 编写一个算法，根据每个人的身高 h 重建这个队列，使之满足每个整数对 (h, k) 中对人数 k 的要求。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 *
 * @author dongxian
 * @version 0.1
 * @date 20-11-17
 **/
public class ReconstructQueueByHeight {
    public int[][] reconstructQueue(int[][] people) {
        // 现根据身高排序，身高一样根据前面的个数逆序
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            // 根据当前需要空下的位置跳过几个位置
            int space = person[1] + 1;
            for (int i = 0; i < n; i++) {
                if (ans[i] == null) {
                    --space;
                    // 前面空下space位置，则将当前位置给当前的person
                    if (space == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}

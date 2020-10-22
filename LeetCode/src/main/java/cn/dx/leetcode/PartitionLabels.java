package cn.dx.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
 * 同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 * https://leetcode-cn.com/problems/partition-labels/
 * <p>
 * 难度：中等
 * <p>
 * 方法：贪心算法 + 双指针
 * <p>
 * 题目要求，同一个字母在同一个片段，则保存所有字母的最后的位置。
 * 对于每个片段，肯定在当前所有字母的最后位置的最大值处，当能够到达这个最大值，则说明可以分开。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/22
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new LinkedList<>();
        // 记录每个字母的最后出现的位置
        Map<Character, Integer> lastEndIndexs = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            lastEndIndexs.put(S.charAt(i), i);
        }
        int start = 0, end = 0;
        while (end < S.length()) {
            // 双指针，分别指示字段的范文
            for (int i = start; i <= end; i++) {
                end = Math.max(end, lastEndIndexs.get(S.charAt(i)));
            }
            // 找到结束，加入当前片段
            res.add(end - start + 1);
            // 下个片段的开始位置
            start = end + 1;
            end = start;
        }
        return res;
    }
}

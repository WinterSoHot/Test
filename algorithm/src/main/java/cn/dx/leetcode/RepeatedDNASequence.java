package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * <p>
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 *
 * @author gudongxian
 * @date 2021/10/8
 */
public class RepeatedDNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> ans = new HashSet<>();
        int subStringLength = 10;
        int n = s.length();
        Set<String> subStringSet = new HashSet<>();
        for (int i = 0; i <= n - subStringLength; i++) {
            String cur = s.substring(i, i + subStringLength);
            if (subStringSet.contains(cur)) {
                ans.add(cur);
            } else {
                subStringSet.add(cur);
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        RepeatedDNASequence pds = new RepeatedDNASequence();
        System.out.println(pds.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(pds.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        System.out.println(pds.findRepeatedDnaSequences("AAAAAAAAAAA"));
    }
}

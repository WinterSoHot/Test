package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * HasAllCodes 5409. 检查一个字符串是否包含所有长度为 K 的二进制子串
 *
 * @author dongxian
 * @version 1.0
 * 20-5-30 下午10:39
 **/
public class HasAllCodes {
    public String valueToString(int v, int len) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toBinaryString(v));
        for (int i = sb.length(); i < len; i++) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    public boolean hasAllCodes(String s, int k) {

        int count = (int) Math.pow(2, k);
        Set<String> hasCode = new HashSet<>();
        for (int i = 0; i < s.length() - k + 1; i++) {
            hasCode.add(s.substring(i, i + k));
        }
        return hasCode.size() == count;
    }

    public static void main(String[] args) {
        HasAllCodes hc = new HasAllCodes();
        hc.hasAllCodes("00110", 2);

    }
}

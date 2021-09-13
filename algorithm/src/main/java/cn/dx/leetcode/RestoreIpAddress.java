package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/7
 */
public class RestoreIpAddress {
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];

    /**
     *
     * @param s 字符串
     * @param segStart 开始位置
     * @param segId 当前分块编号
     */
    public void dfs(String s, int segStart, int segId) {
        // 当前编号等于分块数目，表示查找结束
        if (segId == SEG_COUNT) {
            // 如果开始位置等于字符串长度表示刚好分配好
            if (segStart == s.length()) {
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; i++) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append(".");
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }
        // 提前分配结束，回溯
        if (s.length() == segStart) {
            return;
        }

        // IP前导符为0，只能为0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segStart + 1, segId + 1);
        }

        // 从segStart开始往后查找
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); segEnd++) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segEnd + 1, segId + 1);
            } else {
                break;
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return ans;
    }

    public static void main(String[] args) {
        RestoreIpAddress RIA = new RestoreIpAddress();
        System.out.println(RIA.restoreIpAddresses("25525511135"));
        System.out.println(RIA.restoreIpAddresses("0000"));
        System.out.println(RIA.restoreIpAddresses("010010"));
    }
}

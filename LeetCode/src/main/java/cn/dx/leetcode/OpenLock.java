package cn.dx.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 方法：
 * BFS 广度优先搜索，求出最短路径。
 *
 * 四位锁，每次拨动一位，可以上下波动，即产生8中情况。
 * 等价一个节点和8个点相连。即在这个途中找出从“0000”到目标的最短路径。因此使用BFS可以解决。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/2
 */
public class OpenLock {
    public int openLock(String[] deadends, String target) {
        // 记录待访问扩散节点
        Queue<String> q = new LinkedList<>();
        // 记录已经访问节点，避免重复访问
        Set<String> visited = new HashSet<>();
        // 开始节点进入
        q.offer("0000");
        visited.add("0000");

        // 锁死节点
        List<String> collect = Arrays.stream(deadends).collect(Collectors.toList());
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                // 出队
                String cur = q.poll();
                // 当前节点是否为锁死节点
                if (collect.contains(cur)) {
                    continue;
                }
                // 是否为结束节点
                if (cur.equals(target)) {
                    return step;
                }

                // 从当前节点向四周扩散
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    String down = minusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 扩散结束，路径++
            step++;
        }
        return -1;
    }

    /**
     * 指定位置 +1
     * @param s 字符串
     * @param j 位置
     * @return 修改字符串
     */
    public String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }
    /**
     * 指定位置 -1
     * @param s 字符串
     * @param j 位置
     * @return 修改字符串
     */
    public String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') {
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }

    public static void main(String[] args) {
        OpenLock ol = new OpenLock();
        System.out.println(ol.openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"},
                "8888"));
    }
}

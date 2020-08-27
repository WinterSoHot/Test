package cn.dx.leetcode;

import java.util.*;

/**
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reconstruct-itinerary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/reconstruct-itinerary/solution/zhong-xin-an-pai-xing-cheng-by-leetcode-solution/
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/27
 */
public class ReconstructItinerary {
    /**
     * 保存出发点和目的点，因为按照字典序排序使用优先队列
     */
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> ans = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        findRoad("JFK");
        Collections.reverse(ans);
        return ans;
    }

    /**
     * 深度优先搜索
     *
     * @param nextSquare
     */
    private void findRoad(String nextSquare) {
        while (map.containsKey(nextSquare) && map.get(nextSquare).size() > 0) {
            String tmp = map.get(nextSquare).poll();
            findRoad(tmp);
        }
        // 子节点找完后再添加节点，防止子节点堵住，因此结果是相反的
        ans.add(nextSquare);
    }

    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));
        System.out.println(ri.findItinerary(tickets));
    }
}

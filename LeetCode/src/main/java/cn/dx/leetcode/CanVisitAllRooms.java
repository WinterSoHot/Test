package cn.dx.leetcode;

import java.util.*;

/**
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * <p>
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * <p>
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * <p>
 * 你可以自由地在房间之间来回走动。
 * <p>
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keys-and-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 难度：中等
 *
 * 思路：Set去重，保存能访问的房间，最后比较
 *
 * 官方思路：等同于有向图，问从0开始能否遍历全部节点。
 * - 深度优先搜索
 * - 广度优先搜索
 *
 * 用vis数组标记是否访问，避免重读访问
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/31
 */
public class CanVisitAllRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        Set<Integer> canVisitRoom = new HashSet<>();
        canVisitRoom.add(0);
        Deque<Integer> key = new ArrayDeque<>(rooms.get(0));
        while (!key.isEmpty()) {
            int curKey = key.poll();
            if (!canVisitRoom.contains(curKey)){
                key.addAll(rooms.get(curKey));
                canVisitRoom.add(curKey);
            }
        }
        return canVisitRoom.size() == N;
    }
}

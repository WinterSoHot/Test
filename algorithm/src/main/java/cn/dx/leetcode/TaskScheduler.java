package cn.dx.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * <p>
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/5
 **/
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int res = 0;
        Map<Character, Integer> taskWait = new HashMap<>();
        Map<Character, Integer> taskCount = new HashMap<>();
        for (char task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
            taskWait.put(task, 0);
        }
        while (!taskCount.isEmpty()) {
            Map.Entry<Character, Integer> entry = taskWait.entrySet().stream().sorted(new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    // 等待时间不一样，按时间排序
                    int dis = o1.getValue() - o2.getValue();
                    // 时间一样，按照剩余任务个数，个数越多越先执行
                    int coutnDis = taskCount.get(o2.getKey()) - taskCount.get(o1.getKey());
                    if (dis == 0) {
                        return coutnDis;
                    }
                    return dis;
                }
            }).findFirst().get();
            Character task = entry.getKey();
            Integer wait = entry.getValue();
            Integer count = taskCount.getOrDefault(task, 0);
            count--;

            res += wait + 1;

            for (Map.Entry<Character, Integer> next : taskWait.entrySet()) {
                int value = next.getValue() - wait - 1;
                next.setValue(Math.max(value, 0));
            }
            if (count == 0) {
                taskCount.remove(task);
                taskWait.remove(task);
            } else {
                taskCount.put(task, count);
                taskWait.put(task, n);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();
        int res = ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'
        }, 2);
        System.out.println(res);
    }
}

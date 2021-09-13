package cn.dx;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/7
 */
public class Main13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] tasks = in.nextLine().split(",");
        int taskLen = tasks.length;
        int[] taskTime = new int[taskLen];
        for (int i = 0; i < taskLen; i++) {
            taskTime[i] = Integer.parseInt(tasks[i]);
        }
        String[] deps = in.nextLine().split(",");
        int[] res = new int[taskLen];
        int curTime = 0;
        Map<Integer, List<Integer>> depList = new HashMap<>();
        for (String dep : deps) {
            String[] depItem = dep.split("->");
            int start = Integer.parseInt(depItem[0]);
            List<Integer> preList = depList.getOrDefault(start, new ArrayList<>());
            int end = Integer.parseInt(depItem[1]);
            preList.add(end);
            depList.put(start, preList);
        }
        int count = 0;
        boolean[] visited = new boolean[taskLen];
        while (true) {
            if (count == taskLen) {
                break;
            }
            for (int i = 0; i < taskLen; i++) {
                if (visited[i]) {
                    continue;
                }
                List<Integer> depPre = depList.get(i);
                if (depPre == null) {
                    curTime += taskTime[i];
                    res[i] = curTime;
                    count++;
                    visited[i] = true;
                    continue;
                }
                List<Integer> newList = depPre.stream().filter((v) -> !visited[v]).collect(Collectors.toList());
                if (newList.size() == 0) {
                    curTime += taskTime[i];
                    res[i] = curTime;
                    visited[i] = true;
                    count++;
                } else {
                    depList.put(i, newList);
                }
            }
        }
        for (int i = 0; i < taskLen; i++) {
            if (i == taskLen - 1) {
                System.out.print(res[i]);
                break;
            }
            System.out.print(res[i] + ",");
        }

    }
}

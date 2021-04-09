package cn.dx;

import java.util.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/12
 */
public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, m;
        n = in.nextInt();
        m = in.nextInt();

//        int[][] contact = new int[m][2];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int key = in.nextInt();
            int value = in.nextInt();
            List<Integer> pre = map.getOrDefault(key, new ArrayList<>());
            pre.add(value);
            map.put(key, pre);

//            contact[i][0] = in.nextInt();
//            contact[i][1] = in.nextInt();
        }

        int c = in.nextInt();
        for (int i = 0; i < c; i++) {
            int s = in.nextInt();
            int e = in.nextInt();
            Deque<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[n];
            int res = 0;
            q.offer(s);
            visited[s - 1] = true;
            while (!q.isEmpty()) {
                int sz = q.size();
                for (int j = 0; j < sz; j++) {
                    int p = q.pollFirst();
                    List<Integer> list = map.getOrDefault(p, new ArrayList<>());
                    for (Integer d : list) {
                        if (!visited[d - 1]) {
                            q.offer(d);
                            visited[d - 1] = true;
                        }
                    }
//                    for (int[] cont : contact) {
//                        if (cont[0] == p && !visited[cont[1] - 1]) {
//                            q.offer(cont[1]);
//                            visited[cont[1] - 1] = true;
//                        }
//                    }
                }
                res++;
                if (q.contains(e)) {
                    break;
                }
            }
            System.out.println(q.contains(e) ? res : -1);
        }

    }

}

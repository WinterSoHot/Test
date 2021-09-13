package cn.dx;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/12
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            boolean[] visited = new boolean[n + 1];
            Arrays.fill(visited, false);
            int res = 0;
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                if (x == y) {
                    visited[x] = true;
                    continue;
                }
                if (!visited[x]) {
                    visited[x] = true;
                    res++;
                    continue;
                }
                if (!visited[y]) {
                    visited[y] = true;
                    res++;
                    continue;
                }
                for (int v = 1; v <= n; v++) {
                    if (!visited[v]) {
                        visited[v] = true;
                    }
                }
                res += 2;
            }
            System.out.println(res);
        }
    }
}

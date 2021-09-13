package cn.dx;

import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/28
 */
public class Main8 {
    static int ans = 0;
    static int k;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        k = scanner.nextInt();
        int[][] paths = new int[n][2];
        for (int i = 0; i < n; i++) {
            paths[i][0] = scanner.nextInt();
            paths[i][1] = scanner.nextInt();
        }
        backtrack(paths, m, 0, paths[0][1], 0);
        System.out.println(ans);
    }

    private static void backtrack(int[][] paths, int m, int len, int value, int count) {
        if (count == k) {
            ans = Math.max(ans, value);
            return;
        }
        ans = Math.max(ans, value);
        for (int[] path : paths) {
            int dis = path[0];
            int curValue = path[1];
            if (dis - len > 0 && dis - len <= m) {
                backtrack(paths, m, dis, value + curValue, count + 1);
            }
        }
    }
}

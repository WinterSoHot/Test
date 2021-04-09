package cn.dx;

import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/13
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] input = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                input[i][j] = sc.nextInt();
            }
        }
        int[][] output = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                output[j][i] = input[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }


    }
}

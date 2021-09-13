package cn.dx;

import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/26
 */
public class Main7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int[] input = new int[n];
            for (int x = 0; x < n; x++) {
                input[x] = scanner.nextInt();
            }
            int left = 0, right = 0;
            int ret = 0;
            int match = 0;
            while (right < n) {
                if (input[right] == 0) {
                    match++;
                }
                right++;
                while (match > 1) {
                    if (input[match] == 0) {
                        match--;
                    }
                    left++;
                }
                ret = Math.max(ret, right - left + 1 - match);
            }
            System.out.println(ret);
        }
    }
}

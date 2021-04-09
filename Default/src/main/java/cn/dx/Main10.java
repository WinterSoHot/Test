package cn.dx;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/4
 */
public class Main10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int[] input = new int[n];
            for (int x = 0; x < n; x++) {
                input[x] = scanner.nextInt();
            }
            // 如果就一个实习生
            if (n == 1) {
                System.out.println(input[0]);
                continue;
            }
            // 排序
            Arrays.sort(input);
            // 2个一组分组
            int countGroup = n >> 1;
            int res = 0;
            // 加入有无穷平衡棒
            for (int c = 0; c < countGroup; c++) {
                // 加上每组较大的实习生的时间
                res += input[c * 2 + 1];
            }
            // 计算平衡棒的时间
            if (countGroup > 1) {
                // 每次都由最小时间的一组送回平衡棒
                // input[0] 送回去
                // input[1] 去接input[0]
                // input[0] input[1] 一起回去 算 input[1]时间
                res += input[0] + 2 * input[1];
            }
            // 如果奇数个的话，需要input[0] 接最后一个人
            if ((n & 1) == 1) {
                // 送回去 input[0]  一起回去 input[n - 1]
                res += input[0] + input[n - 1];
            }
            System.out.println(res);
        }
    }
}

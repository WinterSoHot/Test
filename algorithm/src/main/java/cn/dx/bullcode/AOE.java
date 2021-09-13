package cn.dx.bullcode;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author dongxian
 * @version 1.0
 * @className AOE
 * @description TODO
 * @date 20-5-17 下午8:46
 **/
public class AOE {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] arr = scan.nextLine().trim().split(" ");
        int n = Integer.parseInt(arr[0]);
        int x = Integer.parseInt(arr[1]);
        String[] monster = scan.nextLine().trim().split(" ");
        List<Integer> monsters = Arrays.stream(monster)
                .map(Integer::parseInt).sorted()
                .collect(Collectors.toList());
        int res = 0;
        int maxX;
        if (x == 1) {
            System.out.println(monsters.get(n - 1));
            return;
        }
        if (x >= n) {
            for (Integer integer : monsters) {
                res += integer;
            }
            System.out.println(res);
            return;
        } else
            maxX = monsters.get(n - x - 1);
        res += maxX * x;
        for (int i = n - x; i < n; i++) {
            int rest = monsters.get(i) - maxX;
            res += Math.max(rest, 0);
        }
        System.out.println(res);
    }
}

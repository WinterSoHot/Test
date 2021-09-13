package cn.dx;

import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/13
 */
public class Main5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }
        TreeMap<Integer, Integer> num4Count = new TreeMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            int value = num4Count.getOrDefault(input[i], 0) + 1;
            num4Count.put(input[i], value);
            if (stack.isEmpty() || value > stack.peek()) {
                stack.push(input[i]);
            }
        }
        System.out.println(stack.peek());
        for (int i = k; i < n; i++) {
            if (input[i] == input[i - k]) {
                System.out.println(stack.peek());
                continue;
            }
            Integer top = num4Count.get(input[stack.peek()]);
            if (stack.peek() == input[i - k]) {
                top -= 1;
            }
            Integer val = num4Count.getOrDefault(input[i], 0);
            Integer v = num4Count.get(input[i - k]) - 1;
            num4Count.put(input[i], val + 1);
            if (val > top) {
                stack.push(input[i]);
            }
            if (val.equals(top) && stack.peek() > input[i]) {
                stack.push(input[i]);
            }
            if (v == 0) {
                num4Count.remove(v);
            } else {
                num4Count.put(input[i - k], v);
            }
            System.out.println(stack.peek());
        }

    }
}

package cn.dx.offer;

import java.util.Stack;

/**
 * 基本计算器II
 * <p>
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 整数除法仅保留整数部分。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/11
 */
public class Calculate2 {
    public int calculate(String s) {
        // 操作数
        Stack<Integer> vstk = new Stack<>();
        // 操作
        Stack<Character> opstk = new Stack<>();

        boolean preFlag = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isWhitespace(ch)) {
                continue;
            }
            if (Character.isDigit(ch)) {
                if (preFlag) {
                    Integer preV = vstk.pop();
                    vstk.push(preV * 10 + ch - '0');
                } else {
                    preFlag = true;
                    vstk.push(ch - '0');
                }

                // 判断操作栈上面是否为除法和乘法
                if (!opstk.isEmpty() && (opstk.peek() == '/' || opstk.peek() == '*')) {
                    Character op = opstk.pop();
                    Integer second = vstk.pop();
                    Integer first = vstk.pop();
                    if (op == '/') {
                        vstk.push(first / second);
                    } else {
                        vstk.push(first * second);
                    }
                }
            } else {
                preFlag = false;
                opstk.push(ch);
            }
        }
        while (!opstk.isEmpty()) {
            Character op = opstk.pop();
            Integer second = vstk.pop();
            Integer first = vstk.pop();
            switch (op) {
                case '-':
                    vstk.push(first - second);
                    break;
                case '+':
                    vstk.push(first + second);
                    break;
                default:
            }
        }
        return vstk.peek();

    }
}

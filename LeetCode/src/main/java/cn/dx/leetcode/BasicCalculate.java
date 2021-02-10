package cn.dx.leetcode;

import java.util.Stack;

/**
 * 基本计算器
 * <p>
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/2/10
 **/
public class BasicCalculate {
    public int calculate(String s) {
        // 操作数栈
        Stack<Integer> valStack = new Stack<>();
        // 操作栈
        Stack<Character> opStack = new Stack<>();
        boolean isDigitPre = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                // 数字 注意多位数字，如果前面的字符是数字
                if (isDigitPre) {
                    valStack.push(valStack.pop() * 10 + (ch - '0'));
                } else {
                    valStack.push(ch - '0');
                    isDigitPre = true;
                }
            } else {
                // 操作
                isDigitPre = false;
                // 操作数 ) 计算  ()之间的操作
                if (ch == ')') {
                    while ('(' != opStack.peek()) {
                        eval(valStack, opStack);
                    }
                    opStack.pop();
                } else if (ch == '(') {
                    opStack.push(ch);
                } else {
                    // 如果遇到操作+ - 就直接计算
                    if (!opStack.isEmpty() && opStack.peek() != '(') {
                        eval(valStack, opStack);
                    }
                    opStack.push(ch);
                }
            }
        }
        while (!opStack.isEmpty()) {
            eval(valStack, opStack);
        }
        return valStack.peek();
    }

    private void eval(Stack<Integer> valStack, Stack<Character> opStack) {
        Integer right = valStack.pop();
        Integer left = 0;
        if (!valStack.isEmpty()) {
            left = valStack.pop();
        }
        switch (opStack.pop()) {
            case '+':
                valStack.push(right + left);
                break;
            case '-':
                valStack.push(left - right);
                break;
            default:
        }
    }

    public static void main(String[] args) {
        BasicCalculate bc = new BasicCalculate();
        System.out.println(bc.calculate("1 + 1"));
        System.out.println(bc.calculate(" 2-1 + 2 "));
        System.out.println(bc.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(bc.calculate("-2+ 1"));
    }
}

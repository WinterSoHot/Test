package cn.dx.leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/14
 */
public class ValidParentheses {
    /**
     * 简单，用栈模拟操作
     * @param s 输入串
     * @return 是否有效
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }else {
                if (stack.size() == 0){
                    return false;
                }
                switch (ch){
                    case ')':
                        if (stack.pop() != '('){
                            return false;
                        }
                        break;
                    case '}':
                        if (stack.pop() != '{'){
                            return false;
                        }
                        break;
                    case ']':
                        if (stack.pop() != '['){
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }

        }
        return stack.isEmpty();
    }
}

package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * GenerateParenthesis TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-6-4 上午10:24
 **/
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        findBranch(0, n, 0, res, "");
        return res;
    }

    /**
     * 找到下一个分支
     * @param c 当前已经匹配的括号对个数
     * @param n 需要的括号对个数
     * @param flag 标记现在 ( 的个数 == 带加入的 ) 个数
     * @param res 结果保存
     * @param s 字符串保存
     */
    private void findBranch(int c, int n, int flag, List<String> res, String s) {
        if (c == n) {
            // 括号对足够，则加入结果列表
            res.add(s);
            return;
        }
        if (flag != 0) {
            // ( 大于0表示可以查找 ) 分支
            findBranch(c + 1, n, flag - 1, res, s + ")");
        }
        if (s.length() + flag != n*2)
            // 如果当前字符串长度 + 带加入的)个数 和 括号对不相等的话才能 继续匹配 (，否则只能匹配 )
            findBranch(c, n, flag + 1, res, s + "(");

    }

    public static void main(String[] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        System.out.println(gp.generateParenthesis(1));
    }
}

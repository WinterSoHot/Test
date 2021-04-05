package cn.dx.offer;

import java.util.ArrayList;

/**
 * 和为S的连续正数序列
 * <p>
 * 题目描述
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class ContinuousSequenceSum {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        for (int i = 1; i < sum; i++) {
            dfs(sum, i, path, ans);
        }
        return ans;
    }

    private void dfs(int sum, int start, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> ans) {
        if (sum < 0) {
            return;
        }
        if (sum == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        path.add(start);
        dfs(sum - start, start + 1, path, ans);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        ContinuousSequenceSum css = new ContinuousSequenceSum();
        ArrayList<ArrayList<Integer>> res = css.FindContinuousSequence(9);
        System.out.println(res);
    }
}

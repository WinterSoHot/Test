package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 * https://leetcode-cn.com/classic/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/description/
 *
 * 难度：简单
 *
 * 方法：BFS
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/8
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        List<Integer> tmp = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int sz = q.size();
            tmp.clear();
            // 扩散
            for (int i = 0; i < sz; i++) {
                TreeNode par = q.poll();
                tmp.add(par.val);

                // 入队
                if (null!=par.left){
                    q.offer(par.left);
                }
                if (null!=par.right){
                    q.offer(par.right);
                }
            }
            ans.add(new ArrayList<>(tmp));
        }
        return ans;
    }
}

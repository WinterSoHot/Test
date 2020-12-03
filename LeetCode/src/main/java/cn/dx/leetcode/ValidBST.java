package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn08xg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class ValidBST {
    List<Integer> list = new LinkedList<>();

    public boolean isValidBST(TreeNode root) {
        return DFS(root);
    }

    private boolean DFS(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!DFS(node.left)) {
            return false;
        }
        if (list.size() > 0 && node.val <= list.get(list.size() - 1)) {
            return false;
        } else {
            list.add(node.val);
        }
        return DFS(node.right);
    }
}

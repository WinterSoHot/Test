package cn.dx.leetcode;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 难度：简单
 *
 * 注意：二叉搜素树，左子树小于当前节点，右子树大于当前节点，则公共祖先为当前节点在两个节点之间成立。
 *
 * 根据当前节点和最大最小值的关系往下搜素
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/29
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int max = Math.max(p.val, q.val);
        int min = Math.min(p.val, q.val);
        if (root.val >= min && root.val <= max){
            return root;
        }
        while (true) {
            if (root.val >= min && root.val <= max){
                return root;
            }
            if (root.val > max) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }


}

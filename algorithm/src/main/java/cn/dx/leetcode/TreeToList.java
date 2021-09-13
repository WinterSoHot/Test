package cn.dx.leetcode;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * <p>
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * <p>
 * 作者：Krahets
 * 链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/5dbies/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/26
 */
public class TreeToList {
    Node head, cur;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        head.left = cur;
        cur.right = head;
        return head;
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (head == null) {
            head = node;
            cur = head;
        } else {
            cur.right = node;
            node.left = cur;
            cur = node;
        }
        inOrder(node.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}

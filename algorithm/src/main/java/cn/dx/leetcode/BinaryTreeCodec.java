package cn.dx.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author dongxian
 * @version 0.1
 * @date 2021/1/16
 **/
public class BinaryTreeCodec {
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> ret = new LinkedList<>();
        boolean flag = true;
        while (!q.isEmpty() && flag) {
            int sz = q.size();
            flag = false;
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                if (node == null) {
                    ret.add(null);
                } else {
                    ret.add(node.val);
                    q.offer(node.left);
                    q.offer(node.right);
                    if (node.left != null || node.right != null) {
                        flag = true;
                    }
                }
            }
        }
        return Arrays.toString(ret.toArray());
    }

    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        String nodes = data.substring(1, data.length() - 1);
        String[] val = nodes.split(",");
        System.out.println(val.length);
        if (val.length == 0) {
            return null;
        }
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(val[index]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty() && index < val.length - 1) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                String left = val[++index].trim();
                String right = val[++index].trim();
                if (node == null) {
                    continue;
                }
                if ("null".equals(left)) {
                    node.left = null;
                } else {
                    node.left = new TreeNode(Integer.parseInt(left));
                    q.offer(node.left);
                }
                if ("null".equals(right)) {
                    node.right = null;
                } else {
                    node.right = new TreeNode(Integer.parseInt(right));
                    q.offer(node.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeCodec btc = new BinaryTreeCodec();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        String serialize = btc.serialize(root);
        System.out.println(serialize);
        TreeNode data = btc.deserialize(serialize);
        System.out.println(data);
    }
}

package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongxian
 * @version 1.0
 * @className GoodNodes
 * @description TODO
 * @date 20-5-16 下午10:56
 **/
public class GoodNodes {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public int count =0;
    public int goodNodes(TreeNode root) {
        List<Integer> pathVal = new ArrayList<>();
        int count = 0;
        DFS(root,pathVal);
        return count;
    }

    private void DFS(TreeNode root, List<Integer> pathVal) {
        pathVal.add(root.val);
        int maxVal = root.val;
        for (Integer val : pathVal) {
            if (val > maxVal){
                maxVal = val;
            }
        }
        if (maxVal == root.val){
            count = count+1;
        }
        if (root.left!= null){
            DFS(root.left,pathVal);
        }
        if (root.right!=null){
            DFS(root.right,pathVal);
        }
        pathVal.remove(pathVal.size()-1);
    }
}

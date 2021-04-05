package cn.dx.offer;

/**
 * 二叉搜索树的后序遍历序列
 * <p>
 * 题目描述
 * <p>
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。
 * （ps：我们约定空树不是二叉搜素树）
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/10
 */
public class VerifyBST {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        // 校验范围
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int start, int end) {
        // 当前没有区间
        if (start >= end) {
            return true;
        }
        // 作为根节点得元组
        int root = sequence[end];
        // 找到左子树得边界
        int i;
        for (i = start; i < end; i++) {
            if (sequence[i] > root) {
                break;
            }
        }
        // 看剩余区间是否违反右子树大于根得情况
        for (int j = i + 1; j < end; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }
        // 校验左右子树
        return verify(sequence, start, i - 1) && verify(sequence, i, end - 1);
    }
}

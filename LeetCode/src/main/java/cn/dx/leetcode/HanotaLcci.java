package cn.dx.leetcode;

import java.util.List;

/**
 * 面试题 08.06. 汉诺塔问题
 * <p>
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * <p>
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * <p>
 * 你需要原地修改栈。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/1
 */
public class HanotaLcci {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        move(n, A, B, C);
    }

    /**
     * 将 a 中的n个元素从 a 移动到 b
     *
     * @param n 元素个数
     * @param a 从小到大堆叠
     * @param b 中转盘子
     * @param c 目标盘子
     */
    private void move(int n, List<Integer> a, List<Integer> b, List<Integer> c) {
        if (n == 1) {
            // 基础条件 只有一个，直接移动到c上
            c.add(a.remove(a.size() - 1));
            return;
        }
        // 首先将n - 1个元素移动到b上
        move(n - 1, a, c, b);
        // 最大的元素移动到c上
        c.add(a.remove(a.size() - 1));
        // 将b中的n-1个元素移动到c上
        move(n - 1, b, a, c);
    }
}

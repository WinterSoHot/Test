package cn.dx.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 面试题 01.08. 零矩阵
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * https://leetcode-cn.com/problems/zero-matrix-lcci/
 *
 * 难度：中等
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/4
 */
public class ZeroMatrix {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowFlag = new HashSet<>();
        Set<Integer> colFlag = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0){
                    rowFlag.add(i);
                    colFlag.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rowFlag.contains(i) || colFlag.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }

    }
}

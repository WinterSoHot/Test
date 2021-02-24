package cn.dx.leetcode;

/**
 * 832. 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 示例 1:
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/24
 */
public class FlipAnImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        for (int x = 0; x < row; x++) {
            for (int i = 0; i < col / 2; i++) {
                int tmp = A[x][i];
                A[x][i] = A[x][col - i - 1] ^ 1;
                A[x][col - i - 1] = tmp ^ 1;
            }
            if (col % 2 == 1) {
                // 宽为奇数，反转中间的数
                A[x][col / 2] = A[x][col / 2] ^ 1;
            }
        }
        return A;
    }
}

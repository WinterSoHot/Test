package cn.dx.bullcode;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/1
 */
public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            if (array[i - 1] > array[i]) {
                return array[i];
            }
        }
        return -1;
    }
}

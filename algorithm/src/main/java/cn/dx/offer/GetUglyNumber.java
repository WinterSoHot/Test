package cn.dx.offer;

/**
 * 丑数
 * <p>
 * 题目描述
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * <p>
 * 丑数 = 2^x3^y5^z
 * 将当前得到得丑数不断得乘以2，3，5
 * <p>
 * 有可能出现x y z不一样得情况
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class GetUglyNumber {
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) {
            return 0;
        }
        // 跟踪2 3 5上一次得位置
        // 初始化三个指向三个潜在成为最小丑数的位置
        int p2 = 0, p3 = 0, p5 = 0;
        int[] result = new int[index];
        result[0] = 1;
        for (int i = 1; i < index; i++) {
            // 选择最小的填充到当前位置
            result[i] = Math.min(result[p2] * 2, Math.min(result[p3] * 3, result[p5] * 5));
            // 看选择的是那个，可能出现重复复，所以三次选择，重复的往后移动
            // //为了防止重复需要三个if都能够走到
            if (result[i] == result[p2] * 2) {
                p2++;
            }
            if (result[i] == result[p3] * 3) {
                p3++;
            }
            if (result[i] == result[p5] * 5) {
                p5++;
            }
        }
        return result[index - 1];
    }
}

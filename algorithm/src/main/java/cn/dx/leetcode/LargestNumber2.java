package cn.dx.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 179. 最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/12
 */
public class LargestNumber2 {
    public String largestNumber(int[] nums) {
        List<Integer> input = Arrays.stream(nums).boxed().sorted((x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        }).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Integer item : input) {
            sb.append(item);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber2 ln = new LargestNumber2();
        System.out.println(ln.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }
}

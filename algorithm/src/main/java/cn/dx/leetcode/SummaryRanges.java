package cn.dx.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/10
 **/
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new LinkedList<>();
        if (nums.length == 0) {
            return ret;
        }
        boolean flag = true;
        int pre = Integer.MIN_VALUE;
        int start = Integer.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            if (flag) {
                start = num;
                pre = num;
                count = 1;
                flag = false;
            } else {
                if (num - pre == 1) {
                    pre = num;
                    count++;
                } else {
                    if (count == 1) {
                        ret.add(start + "");
                    } else {
                        ret.add(start + "->" + pre);
                    }
                    start = num;
                    count = 1;
                    pre = num;
                }
            }
        }
        if (count == 1) {
            ret.add(start + "");
        } else {
            ret.add(start + "->" + pre);
        }
        return ret;
    }

    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();
        List<String> ret = sr.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9});
        System.out.println(ret);
    }
}

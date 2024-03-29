package cn.dx.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * <p>
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> num2Count = new HashMap<>();
        List<Integer> res = new LinkedList<>();
        for (int num : nums1) {
            num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            Integer occurCount = num2Count.getOrDefault(num, 0);
            if (occurCount > 0) {
                res.add(num);
                num2Count.put(num, occurCount - 1);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}

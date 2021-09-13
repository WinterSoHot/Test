package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * <p>
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * <p>
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 * <p>
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * <p>
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/positions-of-large-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/5
 */
public class LargeGroupPositions {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ret = new ArrayList<>();
        int start = -1;
        char cur = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (start == -1) {
                cur = ch;
                start = i;
            } else {
                if (ch != cur) {
                    if (i - start >= 3) {
                        ret.add(Arrays.asList(start, i - 1));
                    }
                    cur = ch;
                    start = i;
                }
            }
        }
        if (chars.length - start >= 3) {
            ret.add(Arrays.asList(start, chars.length - 1));
        }
        return ret;
    }

    public static void main(String[] args) {
        LargeGroupPositions lgp = new LargeGroupPositions();
        System.out.println(lgp.largeGroupPositions("abbxxxxzzy"));
        System.out.println(lgp.largeGroupPositions("abcdddeeeeaabbbcd"));
    }
}

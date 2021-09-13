package cn.dx.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/14
 */
public class FindCommonCharacter {
    public List<String> commonChars(String[] A) {
        int[] count = new int[26];
        boolean isFirst = true;
        Arrays.fill(count, 0);
        for (String s : A) {
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) {
                int i = ch - 'a';
                freq[i] = freq[i] + 1;
            }
            if (isFirst) {
                for (int i = 0; i < 26; i++) {
                    count[i] = freq[i];
                }
                isFirst = false;
            } else {
                for (int i = 0; i < 26; i++) {
                    count[i] = Math.min(count[i], freq[i]);
                }
            }
        }
        List<String> res = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            int c = count[i] / A.length;
            for (int j = 0; j < c; j++) {
                res.add((char) (i + 'a') + "");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindCommonCharacter fcc = new FindCommonCharacter();
        fcc.commonChars(new String[]{"bella", "label", "roller"});
    }
}

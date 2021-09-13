package cn.dx.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2021/1/22
 **/
public class AddToArrayForm {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new LinkedList<>();
        int cal = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            int bit = A[i] + cal + K % 10;
            ans.add(bit % 10);
            cal = bit / 10;
            K /= 10;
        }
        while (K != 0) {
            int bit = cal + K % 10;
            ans.add(bit % 10);
            cal = bit / 10;
            K /= 10;
        }
        if (cal != 0) {
            ans.add(cal);
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        AddToArrayForm at = new AddToArrayForm();
        System.out.println(at.addToArrayForm(new int[]{1, 2, 0, 0}, 34));
        System.out.println(at.addToArrayForm(new int[]{2, 7, 4}, 181));
        System.out.println(at.addToArrayForm(new int[]{2, 1, 5}, 806));
    }
}

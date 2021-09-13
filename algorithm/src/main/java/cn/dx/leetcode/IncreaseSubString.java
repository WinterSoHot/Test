package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * <p>
 * 示例:
 * <p>
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 * <p>
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/25
 */
public class IncreaseSubString {

    private int n;
    private List<Integer> temp = new ArrayList<>();
    private List<List<Integer>> ans = new ArrayList<>();
    private Set<Integer> set =new HashSet<>();

    /**
     * 根据当前的mask找子串
     * @param mask mask，标记当前那些数字被选中
     * @param nums 数组
     */
    public void findSubSeq(int mask,int [] nums){
        //清除临时数组
        temp.clear();
        for (int i = 0; i < n; i++) {
            // mask二进制位置为1的话，表示当前位置的数组元素被选中
            if ((mask & 1) != 0){
                temp.add(nums[i]);
            }
            // 往右移位
            mask >>= 1;
        }
    }

    /**
     * 检查是否为递增序列
     * @return
     */
    public boolean check(){
        if (temp.size() < 2){
            return false;
        }
        for (int i = 1; i < temp.size(); i++) {
            if (temp.get(i) < temp.get(i-1)){
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        n = nums.length;
        for (int mask = 1; mask < 1 << n; mask++) {
            findSubSeq(mask,nums);
            int hash = getHash(263, (int) 1E9 + 7);
            if (check() && !set.contains(hash)){
                // 创建新的数组，防止引用
                ans.add(new ArrayList<>(temp));
                set.add(hash);
            }
        }
        return ans;
    }

    /**
     * 一种序列的计算hash方法
     * 串哈希算法（即 Rabin-Karp 编码）
     * @param base
     * @param mod
     * @return
     */
    public int getHash(int base, int mod) {
        int hashValue = 0;
        for (int x : temp) {
            hashValue = hashValue * base % mod + (x + 101);
            hashValue %= mod;
        }
        return hashValue;
    }

    public static void main(String[] args) {
        IncreaseSubString iss = new IncreaseSubString();
        System.out.println(iss.findSubsequences(new int[]{}));
    }

}

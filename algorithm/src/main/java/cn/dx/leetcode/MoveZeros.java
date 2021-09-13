package cn.dx.leetcode;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/19
 */
public class MoveZeros {
    /**
     * 直接从尾部开始排序
     *
     * @param nums 数组
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                for (int j = i; j < n - 1 && nums[j + 1] != 0; j++) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    /**
     * 双指针
     *
     * @param nums 数组
     */
    public void moveZeroes2(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        // 左指针指向已经处理好的序列尾部，右指针指向待处理序列的头部
        // 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
        // 注意到以下性质：
        // - 左指针左边均为非零数；
        // - 右指针左边直到左指针处均为零。
        // 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
        while (right < n) {
            if (nums[right] != 0) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
            }
            right++;
        }
    }
}

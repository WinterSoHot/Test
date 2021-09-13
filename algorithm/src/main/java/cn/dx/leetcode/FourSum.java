package cn.dx.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：中等
 * 通过 234/283
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/10/5
 */
public class FourSum {
    List<List<Integer>> res = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();
    Set<String> pathSet = new HashSet<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        nums = Arrays.stream(nums).sorted().toArray();
        backtrack(nums, target, 0);
        return res;
    }

    private void backtrack(int[] nums, int target, int start) {
        if (path.size() > 4) {
            return;
        }
        // 结束条件
        if (target == 0 && path.size() == 4) {
            // 去除重复的结果
            List<Integer> item = new ArrayList<>(path);
            Collections.sort(item);
            String pathStr = item.stream().map(d -> d + "").collect(Collectors.joining("-"));
            if (pathSet.contains(pathStr)) {
                return;
            }
            pathSet.add(pathStr);
            res.add(item);
            return;
        }

        // 选择列表
        for (int i = start; i < nums.length; i++) {
            int v = nums[i];
            path.add(v);
            backtrack(nums, target - v, i + 1);
            path.removeLast();
            if (path.size() == 4 && target - v > 0) {
                break;
            }
        }
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        nums = Arrays.stream(nums).sorted().toArray();
        // 有序后，用双指针
        for (int i = 0; i < nums.length - 3; i++) {
            //避免重复：第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 剪枝
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                // 避免重复，第二个数
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 剪枝
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }

                // 双指针，后两个数
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        List<Integer> item = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        res.add(item);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    }
                }
            }
        }
        return res;
    }
}

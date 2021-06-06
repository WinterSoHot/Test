package cn.dx.leetcode;

import java.util.Arrays;

/**
 * 1723. 完成所有工作的最短时间
 * <p>
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * <p>
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * <p>
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/8
 */
public class MinimumTimeToFinishAllJobs {

    /**
     * https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/solution/wan-cheng-suo-you-gong-zuo-de-zui-duan-s-hrhu/
     *
     * @param jobs
     * @param k
     * @return
     */
    public int minimumTimeRequired2(int[] jobs, int k) {
        int n = jobs.length;
        int[] sum = new int[1 << n];
        for (int i = 1; i < 1 << n; i++) {
            int x = Integer.numberOfTrailingZeros(i), y = i - (1 << x);
            sum[i] = sum[y] + jobs[x];
        }
        int[][] dp = new int[k][1 << n];
        for (int i = 0; i < 1 << n; i++) {
            dp[0][i] = sum[i];
        }

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < 1 << n; j++) {
                int minn = Integer.MAX_VALUE;
                for (int x = j; x != 0; x = (x - 1) & j) {
                    minn = Math.min(minn, Math.max(dp[i - 1][j - x], sum[x]));
                }
                dp[i][j] = minn;
            }
        }
        return dp[k - 1][(1 << n) - 1];
    }

    public int minimumTimeRequired(int[] jobs, int k) {
        int left = Arrays.stream(jobs).max().getAsInt();
        int right = Arrays.stream(jobs).sum();
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(jobs, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] jobs, int k, int mid) {
        int[] workloads = new int[k];
        return backtrack(jobs, workloads, 0, mid);
    }

    private boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {
        if (i >= jobs.length) {
            return true;
        }
        for (int x = 0; x < workloads.length; x++) {
            if (workloads[x] + jobs[i] <= limit) {
                workloads[x] += jobs[i];
                if (backtrack(jobs, workloads, i + 1, limit)) {
                    return true;
                }
                workloads[x] -= jobs[i];
            }

            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
            // 或者当前工作恰能使该工人的工作量达到了上限
            // 这两种情况下我们无需尝试继续分配工作
            if (workloads[x] == 0 || workloads[x] + jobs[i] == limit) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MinimumTimeToFinishAllJobs mttfaj = new MinimumTimeToFinishAllJobs();
        System.out.println(mttfaj.minimumTimeRequired(new int[]{3, 2, 3}, 3));
    }
}

package cn.dx.leetcode;

import java.util.*;

/**
 * MinimumTotal TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-18 下午9:16
 **/
public class MinimumTotal {
    public static int minimumTotal(List<List<Integer>> triangle) {
        
        int row = triangle.size();
        int column = triangle.get(triangle.size() - 1).size();
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> line = triangle.get(i);
            for (int j = 0; j < line.size(); j++) {
                if (j == 0) {
                    line.set(j, line.get(j) + triangle.get(i - 1).get(j));
                } else if (j == line.size() - 1) {
                    line.set(j, line.get(j) + triangle.get(i - 1).get(j - 1));
                } else {
                    line.set(j, Math.min(line.get(j) + triangle.get(i - 1).get(j), line.get(j) + triangle.get(i - 1).get(j - 1)));
                }
            }
        }
        List<Integer> lastList = triangle.get(row - 1);
        System.out.println(lastList);
       int minValue = lastList.get(0);
        for (int i = 1; i < lastList.size(); i++) {
            minValue = Math.min(minValue,lastList.get(i));
        }
        return minValue;


//        int[][] dp = new int[row + 1][column + 1];
//        for (int i = 1; i <= triangle.size(); i++) {
//            List<Integer> line = triangle.get(i - 1);
//            for (int j = 1; j <= line.size(); j++) {
//                if (j == 1) {
//                    dp[i][j] = dp[i - 1][j] + line.get(0);
//                } else if (j == line.size()) {
//                    dp[i][j] = dp[i - 1][j - 1] + line.get(j - 1);
//                } else
//                    dp[i][j] = Math.min(dp[i - 1][j] + line.get(j - 1), dp[i - 1][j - 1] + line.get(j - 1));
//                System.out.print(dp[i][j] + "\t");
//            }
//            System.out.println();
//        }
//        int minValue = dp[row][1];
//        for (int i = 1; i <= column; i++) {
//            minValue = Math.min(minValue, dp[row][i]);
//        }
//        return minValue;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(-1));
        triangle.add(Arrays.asList(-2, -3));
//        triangle.add(Arrays.asList(6, 5, 7));
//        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal(triangle));
    }
}

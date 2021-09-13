package cn.dx.leetcode.weekly.c202;

/**
 * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
 * https://leetcode-cn.com/contest/weekly-contest-202/problems/three-consecutive-odds/
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/16
 */
public class ThreeConsecutiveOdds {


    public boolean isOdd(int v){
        if (v % 2 == 0){
            return false;
        }
        return true;
    }

    public boolean threeConsecutiveOdds(int[] arr) {
        int counter = 0;
        for (int item : arr) {
            if (isOdd(item)){
                counter++;
            }else {
                counter = 0;
            }
            if (counter == 3){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ThreeConsecutiveOdds tco = new ThreeConsecutiveOdds();
        System.out.println(tco.isOdd(1));
        System.out.println(tco.isOdd(3));
        System.out.println(tco.isOdd(2));
    }
}

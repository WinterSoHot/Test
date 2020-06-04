package cn.dx.leetcode;

/**
 * CanBeEqual TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-30 下午10:32
 **/
public class CanBeEqual {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] flag = new int[target.length];
        for (int i = 0; i < target.length; i++) {
            flag[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            for (int j = 0; j < target.length; j++) {
                if (flag[j] == 1) {
                    continue;
                } else {
                    if (tmp == target[j]) {
                        flag[j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < target.length; i++) {
            if (flag[i] == 0) {
                return false;
            }
        }
        return true;
    }
}

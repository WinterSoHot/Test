package cn.dx.leetcode;

/**
 * @author dongxian
 * @version 0.1
 * @date 2020/12/4
 **/
public class VersionControl {
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    boolean isBadVersion(int version) {
        return false;
    }

    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        int mid = (end - start) / 2 + start;
        while (end - start > 1) {
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
            mid = (end - start) / 2 + start;
        }
        return isBadVersion(start) ? start : end;
    }
}

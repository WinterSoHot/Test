package cn.dx.leetcode;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 难度：简单
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/20
 */
public class HappyNum {

    public int bitHappy(int n) {
        int res = 0;
        while (n != 0) {
            int cur = n % 10;
            res += cur * cur;
            n = n / 10;
        }
        return res;
    }


    public boolean isHappy(int n) {
        // 快慢指针，不是快乐数，则一定会循环
        int slow = n, fast = n;
        do {
            slow = bitHappy(slow);
            fast = bitHappy(fast);
            fast = bitHappy(fast);
        } while (slow != fast);
        return slow == 1;
    }
}

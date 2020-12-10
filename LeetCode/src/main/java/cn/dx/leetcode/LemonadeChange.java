package cn.dx.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * <p>
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * <p>
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * <p>
 * 注意，一开始你手头没有任何零钱。
 * <p>
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lemonade-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/10
 **/
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> bill4Count = new HashMap<>(3);
        for (int bill : bills) {
            Integer billFive = bill4Count.getOrDefault(5, 0);
            Integer billTen = bill4Count.getOrDefault(10, 0);
            switch (bill) {
                case 5:
                    bill4Count.put(bill, bill4Count.getOrDefault(bill, 0) + 1);
                    break;
                case 10:
                    if (billFive > 0) {
                        bill4Count.put(5, billFive - 1);
                        bill4Count.put(bill, bill4Count.getOrDefault(bill, 0) + 1);
                    } else {
                        return false;
                    }
                    break;
                case 20:
                    if (billTen > 0 && billFive > 0) {
                        bill4Count.put(5, billFive - 1);
                        bill4Count.put(10, billTen - 1);
                        bill4Count.put(bill, bill4Count.getOrDefault(bill, 0) + 1);
                    } else if (billFive >= 3) {
                        bill4Count.put(5, billFive - 3);
                    } else {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}

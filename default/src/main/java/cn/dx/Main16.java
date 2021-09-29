package cn.dx;

/**
 * @author gudongxian
 * @date 2021/9/28
 */
public class Main16 {
    public Integer count(int x, int n) {
        int[] day4Count = new int[6];
        day4Count[0] = x;
        for (int i = 0; i < n; i++) {
            // 新出生
            int newCount = 0;
            for (int d = 5; d > 0; d--) {
                if (d % 2 == 0) {
                    newCount += day4Count[d] * 2;
                }
                if (d == 4) {
                    day4Count[d] = day4Count[d - 1] - 1;
                } else {
                    day4Count[d] = day4Count[d - 1];
                }
            }
            day4Count[0] = newCount;
        }
        int count = 0;
        for (Integer value : day4Count) {
            count += value;
        }
        return count * 2;
    }

    public static void main(String[] args) {
        Main16 main = new Main16();
        Integer count = main.count(1, 2);
        System.out.println(count);
    }
}

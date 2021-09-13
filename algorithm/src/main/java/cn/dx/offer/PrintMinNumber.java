package cn.dx.offer;

/**
 * 剑指offer-32-把数组排成最小的数
 * <p>
 * 比较两个字符串s1, s2大小的时候，
 * 先将它们拼接起来，比较s1+s2,和s2+s1那个大，如果s1+s2大，
 * 那说明s2应该放前面，所以按这个规则，s2就应该排在s1前面。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/2
 */
public class PrintMinNumber {
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int iv = Integer.parseInt(numbers[i] + "" + numbers[j]);
                int jv = Integer.parseInt(numbers[j] + "" + numbers[i]);
                if (iv > jv) {
                    int tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = tmp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number);
        }
        return sb.toString();
    }
}

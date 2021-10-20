package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gudongxian
 * @date 2021/10/11
 */
public class IntegerToEnglishWords {
    public static Map<Integer, String> value2str = new HashMap<>();
    public static Map<String, Integer> str2value = new HashMap<>();

    static {
        value2str.put(0, "");
        value2str.put(1, "One");
        value2str.put(2, "Two");
        value2str.put(3, "Three");
        value2str.put(4, "Four");
        value2str.put(5, "Five");
        value2str.put(6, "Six");
        value2str.put(7, "Seven");
        value2str.put(8, "Eight");
        value2str.put(9, "Nine");
        value2str.put(10, "Ten");
        value2str.put(11, "Eleven");
        value2str.put(12, "Twelve");
        value2str.put(13, "Thirteen");
        value2str.put(14, "Fourteen");
        value2str.put(15, "Fifteen");
        value2str.put(16, "Sixteen");
        value2str.put(17, "Seventeen");
        value2str.put(18, "Eighteen");
        value2str.put(19, "Nineteen");
        value2str.put(20, "Twenty");
        value2str.put(30, "Thirty");
        value2str.put(40, "Forty");
        value2str.put(50, "Fifty");
        value2str.put(60, "Sixty");
        value2str.put(70, "Seventy");
        value2str.put(80, "Eighty");
        value2str.put(90, "Ninety");
        for (Map.Entry<Integer, String> entry : value2str.entrySet()) {
            str2value.put(entry.getValue(), entry.getKey());
        }
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        List<String> ans = new ArrayList<>();
        int index = 0;
        while (num != 0) {
            int cur = num % 10;
            if (cur == 0) {
                num /= 10;
                index++;
                continue;
            }
            switch (index % 3) {
                case 0:
                    // 千 百万 十亿
                    switch (index / 3) {
                        case 1:
                            ans.add("Thousand");
                            break;
                        case 2:
                            ans.add("Million");
                            break;
                        case 3:
                            ans.add("Billion");
                            break;
                        default:
                            break;
                    }
                    // 个
                    ans.add(value2str.get(cur));
                    break;
                case 1:
                    // 十
                    ans.add(value2str.get(cur * 10));
                    break;
                case 2:
                    // 百
                    ans.add("Hundred");
                    ans.add(value2str.get(cur));
                    break;
                default:
                    break;
            }
            num /= 10;
            index++;
        }
        int size = ans.size();
        if (size >= 2) {
            String last = ans.get(size - 1);
            String lastTwo = ans.get(size - 2);
            if (str2value.containsKey(last) && str2value.containsKey(lastTwo)) {
                Integer lastValue = str2value.get(last);
                Integer lastTwoValue = str2value.get(lastTwo);
                int combine = lastValue + lastTwoValue;
                if (value2str.containsKey(combine)) {
                    ans.remove(size - 1);
                    ans.remove(size - 2);
                    System.out.println("combine = " + combine);
                    ans.add(value2str.get(combine));
                }
            }
        }
        return ans.stream().filter(it -> !"".equals(it)).reduce("", (a, b) -> b + " " + a).trim();
    }

    public static void main(String[] args) {
        IntegerToEnglishWords ite = new IntegerToEnglishWords();
        System.out.println(ite.numberToWords(1234567));
        System.out.println(ite.numberToWords(1234567891));
        System.out.println(ite.numberToWords(12345));
    }
}

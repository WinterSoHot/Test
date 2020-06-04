package cn.dx.leetcode;

/**
 * CountAndSay TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-19 下午11:01
 **/
public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String pre = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < pre.length(); ) {
            int count = 1;
            char curChar = pre.charAt(i);
            while (i+count < pre.length() && curChar == pre.charAt(i + count) ) {
                count++;
            }
            i += count;
            res.append(String.valueOf(count)).append(curChar);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        CountAndSay cs = new CountAndSay();
        System.out.println(cs.countAndSay(1));
        System.out.println(cs.countAndSay(2));
        System.out.println(cs.countAndSay(3));
        System.out.println(cs.countAndSay(4));
        System.out.println(cs.countAndSay(5));
        System.out.println(cs.countAndSay(6));
    }
}

package cn.dx.leetcode;

public class LongestPalindrome {
    public static void main(String[] args) {
        String input = "cfbbacdcabbf";
        if ("".equals(input)){
            System.out.println(input);
        }
        int maxLength = 0;
        int minStart =0, maxEnd=0;
        for (int center = 0; center < 2 * input.length()-1; center++) {
            int tmpRadius = 0;
            boolean isOdd = center % 2 == 1 ? true : false;
            int start,end;
            if (isOdd) {
                //当前是奇数位置，表示在字符中间插槽上。
                //检查两边
                if (input.charAt((center - 1) / 2) == input.charAt((center + 1) / 2)) {
                     start = center - 1;
                     end = center + 1;
                }else {
                    continue;
                }
            } else {
                 start = center;
                 end = center;
            }
            while (start > 0 && end < 2 * input.length() - 2) {
                if (input.charAt((start - 2) / 2) == input.charAt((end + 2) / 2)) {
                    start -= 2;
                    end += 2;
                }else {
                    break;
                }
            }
            if (end - start > maxLength) {
                minStart = start;
                maxEnd = end;
                maxLength = maxEnd - minStart;
            }
        }
        System.out.println(input.substring(minStart / 2, maxEnd / 2 + 1));
    }
}

package cn.dx;

import java.util.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/13
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        StringBuilder sb = new StringBuilder();
        List<String> out = new ArrayList<>();
        char[] chars = s.toCharArray();
        boolean isFlag = false;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isDigit(ch)) {
                if (sb.length() == 0) {
                    if (ch == '0') {
                        if (isFlag) {
                            sb.append(ch);
                        } else {
                            isFlag = true;
                            continue;
                        }
                    }
                } else {
                    isFlag = false;
                    if (sb.charAt(0) == '0') {
                        sb.deleteCharAt(0);
                    }
                    sb.append(ch);
                }
            } else {
                isFlag = false;
                if (sb.length() > 0) {
                    out.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        if (sb.length() > 0) {
            out.add(sb.toString());
        }
        Collections.sort(out, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
        for (String i : out) {
            System.out.println(i);
        }
    }
}

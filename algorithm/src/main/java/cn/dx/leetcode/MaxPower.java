package cn.dx.leetcode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dongxian
 * @version 1.0
 * @className MaxPower
 * @description TODO
 * @date 20-5-16 下午10:35
 **/
public class MaxPower {


    public static void main(String[] args) {
        String s = "hooraaaaaaaaaaay";
        int power = 1;
        if (s.isEmpty()){
//            return 0;
        }
        char lastChar = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == lastChar){
                power++;
            }else {
                power =1;
            }
            lastChar = s.charAt(i);
        }
        return;
    }
}

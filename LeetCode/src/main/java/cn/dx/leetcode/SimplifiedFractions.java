package cn.dx.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongxian
 * @version 1.0
 * @className SimplifiedFractions
 * @description TODO
 * @date 20-5-16 下午10:48
 **/
public class SimplifiedFractions {
    private int gcd(int a,int b) {
        return (b==0)?a:gcd(b,a%b);
    }
    public List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(j,i) >1 ){
                    continue;
                }
                res.add(String.format("%d/%d", j, i));
            }
        }
        return res;
    }
}

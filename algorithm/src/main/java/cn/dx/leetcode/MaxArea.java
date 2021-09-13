package cn.dx.leetcode;

import java.util.Arrays;

/**
 * MaxArea TODO
 *
 * @author dongxian
 * @version 1.0
 * 20-5-31 上午10:55
 **/
public class MaxArea {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long  hSpin = 1;
        long vSpin = 1;
        int lastCuts = 0;
        int curSpin = 1;
        for (int horizontalCut : horizontalCuts) {
            curSpin = horizontalCut - lastCuts;
            if (curSpin > hSpin) {
                hSpin = curSpin;
            }
            lastCuts = horizontalCut;
        }
        curSpin = h - lastCuts;
        if (curSpin > hSpin) {
            hSpin = curSpin;
        }
        lastCuts = 0;
        for (int verticalCut : verticalCuts) {
            curSpin = verticalCut - lastCuts;
            if (curSpin > vSpin) {
                vSpin = curSpin;
            }
            lastCuts = verticalCut;
        }
        curSpin = w - lastCuts;
        if (curSpin > vSpin) {
            vSpin = curSpin;
        }

        return (int) ((vSpin * hSpin) % (Math.pow(10, 9) + 7));
    }
}

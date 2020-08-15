package cn.dx.leetcode;

import java.util.*;

/**
 *
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 *
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 *
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 *
 * 最后返回经过上色渲染后的图像。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flood-fill
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/16
 */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int row = image.length;
        int col = image[0].length;

        int oldColor = image[sr][sc];
        // 保存待检查的点
        Deque<String> colorLoc = new ArrayDeque<>();
        // 保存已经修改后的点
        Set<String> set = new HashSet<>();
        colorLoc.offer(sr + "_" + sc);
        while (!colorLoc.isEmpty()){
            String head = colorLoc.poll();
            if (set.contains(head)){
                continue;
            }
            set.add(head);
            String[] s = head.split("_");
            int tsr = Integer.parseInt(s[0]);
            int tsc = Integer.parseInt(s[1]);
            image[tsr][tsc] = newColor;
            // 检查四个方向
            if (tsr > 0){
                String key = (tsr - 1) + "_" + tsc;
                if (image[tsr-1][tsc] == oldColor){
                    colorLoc.offer(key);
                }
            }
            if (tsr < row - 1){
                String key = (tsr + 1) + "_" + tsc;
                if (image[tsr+1][tsc] == oldColor){
                    colorLoc.offer(key);
                }
            }

            if (tsc > 0){
                String key = tsr + "_" + (tsc - 1);
                if (image[tsr][tsc-1] == oldColor){
                    colorLoc.offer(key);
                }
            }
            if (tsc < col - 1){
                String key = tsr + "_" + (tsc + 1);
                if (image[tsr][tsc+1] == oldColor){
                    colorLoc.offer(key);
                }
            }
        }
        return image;
    }
}

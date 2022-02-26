package cn.dx.java.senior.core;

/**
 * @author gudongxian
 * @date 2022/2/15
 */
public class MovingAverage {
    private int count = 0;
    private double sum = 0.0;

    public void submit(double value) {
        this.count ++;
        this.sum += value;
    }

    public double getAvg() {
        if (count == 0) {
            return sum;
        }
        return this.sum / this.count;
    }
}

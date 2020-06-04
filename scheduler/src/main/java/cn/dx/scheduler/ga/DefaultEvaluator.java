package cn.dx.scheduler.ga;

import java.util.Arrays;

public class DefaultEvaluator implements TargetEvaluation {
    @Override
    public double eval(int[] seq, double[][] data) {// 根据时间序列 和 特征信息 计算适应度， 适应度越大越好
        // 取最短响应比作为目标函数
        double[] reactRate = new double[seq.length];
        double waitTime = 0.0;
        for (int i = 0; i < seq.length; i++) {
            reactRate[i] = (waitTime + data[seq[i]][0]) / data[seq[i]][0];
            waitTime += data[seq[i]][0];
        }
        double averageReactRate = Arrays.stream(reactRate).average().getAsDouble();

        //计算综合优先级
        double averagePriority = 0;
        for (int j = 0; j < seq.length; j++) {
            averagePriority += ((double) j / seq.length) * data[seq[j]][1];
        }

        //因为本算法根据适应度越高表示越好，因此对响应比和权限取倒数
        return 1 / averageReactRate + 1 / averagePriority;
    }
}

package cn.dx.scheduler;


import cn.dx.scheduler.aco.ACO;
import cn.dx.scheduler.ga.SequenceGA;
import cn.dx.scheduler.ga.TargetEvaluation;
import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

public class GAAndACO {

    public static void main(String[] args) {
        // 混合遗传算法和蚁群算法
        // 将 遗传算法的解 作为 蚁群算法 的 初始信息素分布
        int serviceCount = 10;
        int featureCount = 2;
        SequenceGA ga = new SequenceGA(10, 300, 1, 0.015);
        TargetEvaluation eval = new TargetEvaluation() {
            @Override
            public double eval(int[] seq, double[][] data) {
                // 根据时间序列 和 特征信息 计算适应度， 适应度越大越好
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
        };
        ga.initGA(serviceCount, -2, 2, eval);


        //随机生成 serviceCount 个服务 的 特征数据
        // 特征： 运行时间 优先级
        double[][] data = new double[serviceCount][featureCount];
        for (int i = 0; i < serviceCount; i++) {
            for (int j = 0; j < featureCount; j++) {
                data[i][j] = RandomUtil.randomDouble(1, 10);
            }
        }
        ga.start(data);
        System.out.println("遗传算法结果");
        int[] bestTour = ga.getBestIndividual();
        for (int i = 0; i < bestTour.length; i++) {
            String str = i != bestTour.length - 1 ? "->" : "\n";
            System.out.print(bestTour[i] + str);
        }

        // 相邻服务调度的评价
        // 将相邻序列的响应比作为两者之间的距离
        double[][] distance = new double[serviceCount][serviceCount];
        for (int i = 0; i < serviceCount; i++) {
            for (int j = 0; j < serviceCount; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                    continue;
                }
                double[] reactRate = new double[2];
                double waitTime = 0.0;
                reactRate[0] = (waitTime + data[i][0]) / data[i][0];
                waitTime += data[i][0];
                reactRate[1] = (waitTime + data[j][0]) / data[j][0];
                distance[i][j] = Arrays.stream(reactRate).average().getAsDouble();
            }
        }

        ACO aco = new ACO(serviceCount, distance);
        //将遗传算法的结果，表现到信息素上。
        aco.init(2000, ga.oldPopulation);
        //开始迭代
        aco.run(20);


        int[] bestTour1 = aco.getBestTour();
        System.out.println("ACO算法结果");
        for (int i = 0; i < bestTour1.length; i++) {
            String str = i != bestTour1.length - 1 ? "->" : "\n";
            System.out.print(bestTour1[i] + str);
        }
        System.out.println(eval.eval(bestTour1, data));
    }

}

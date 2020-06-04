package cn.dx.scheduler;





import cn.dx.scheduler.aco.ACO;
import cn.dx.scheduler.ga.SequenceGA;
import cn.dx.scheduler.ga.TargetEvaluation;

import java.util.Arrays;

public class ExecutorRuner {

    private int gaMaxGen;
    private int antCount;
    private float gaRateOfCross;
    private int acoMaxGen;
    private float gaRateOfMutate;

    private ExecutorRuner(Builder builder) {
        this.gaMaxGen = builder.gaMaxGen;
        this.antCount = builder.antCount;
        this.gaRateOfCross = builder.gaRateOfCross;
        this.acoMaxGen = builder.acoMaxGen;
        this.gaRateOfMutate = builder.gaRateOfMutate;
    }

    public int[] run(double[][] data, TargetEvaluation eval) {
        int serviceCount = data.length;
        SequenceGA ga = new SequenceGA(serviceCount, gaMaxGen, gaRateOfCross, gaRateOfMutate);
        ga.initGA(serviceCount, -2, 2, eval);
        ga.start(data);
//        System.out.println("遗传算法结果");
//        int[] bestTour = ga.getBestIndividual();
//        for (int i = 0; i < bestTour.length; i++) {
//            String str = i != bestTour.length - 1 ? "->" : "\n";
//            System.out.print(bestTour[i] + str);
//        }


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
        aco.init(antCount, ga.oldPopulation);
        //开始迭代
        aco.run(acoMaxGen);
        return aco.getBestTour();
    }


    public static class Builder {
        public int gaMaxGen = 100;
        public int antCount = 100;
        public float gaRateOfCross;
        public int acoMaxGen = 100;
        public float gaRateOfMutate;

        public Builder() {

        }

        /**
         * 设置遗传算法参数
         *
         * @param gaMaxGen 最大迭代代数
         * @return
         */
        public Builder setGAParameters(int gaMaxGen) {
            this.gaMaxGen = gaMaxGen;
            this.gaRateOfCross = 1.0f;
            this.gaRateOfMutate = 0.15f;
            return this;
        }

        /**
         * 设置遗传算法参数
         *
         * @param gaMaxGen       最大迭代代数
         * @param gaRateOfCross  交叉概率
         * @param gaRateOfMutate 变异概率
         * @return
         */
        public Builder setGAParameters(int gaMaxGen, float gaRateOfCross, float gaRateOfMutate) {
            this.gaMaxGen = gaMaxGen;
            this.gaRateOfCross = gaRateOfCross;
            this.gaRateOfMutate = gaRateOfMutate;
            return this;
        }

        /**
         * 设置蚁群算法参数
         *
         * @param acoCount 蚂蚁个数
         * @return
         */
        public Builder setAcoParameters(int acoCount, int acoMaxGen) {
            this.antCount = acoCount;
            this.acoMaxGen = acoMaxGen;
            return this;
        }

        public ExecutorRuner build() {
            ExecutorRuner runer = new ExecutorRuner(this);
            return runer;
        }
    }
}

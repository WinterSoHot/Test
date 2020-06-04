package cn.dx.scheduler;


import cn.dx.scheduler.aco.ACO;
import cn.dx.scheduler.ga.SequenceGA;
import cn.dx.scheduler.ga.TargetEvaluation;

public class BackUpExecutorRuner {

    private int gaMaxGen;
    private int antCount;
    private float gaRateOfCross;
    private int acoMaxGen;
    private float gaRateOfMutate;

    private BackUpExecutorRuner(Builder builder) {
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
        double[][] distance = new double[serviceCount][serviceCount];
        for (int i = 0; i < serviceCount; i++) {
            for (int j = 0; j < serviceCount; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                    continue;
                }
                double cpuDistance = 0.0;
                //两个加起来不到100%，加起来越小，越可能再容纳一个新的任务，这两个一起执行概率越大，距离越小
                if(data[i][0]+data[j][0]<=1.0){
                    cpuDistance = data[i][0]+data[j][0];
                }
                //两个加起来大于100%小于200%，越接近100，越需要和小任务结合，一起执行概率越小，距离越大，反之越接近200，距离越小
                else{
                    cpuDistance = 2.0-data[i][0]+data[j][0];
                }
                double memDistance = 0.0;
                //存储资源同理
                if(data[i][1]+data[j][1]<=1.0){
                    memDistance = data[i][1]+data[j][1];
                }
                else{
                    memDistance = 2.0-data[i][1]+data[j][1];
                }
                distance[i][j] = memDistance+cpuDistance;
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

        public BackUpExecutorRuner build() {
            BackUpExecutorRuner runer = new BackUpExecutorRuner(this);
            return runer;
        }
    }
}

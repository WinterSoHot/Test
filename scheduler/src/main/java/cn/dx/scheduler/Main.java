package cn.dx.scheduler;

import cn.dx.scheduler.ga.DefaultEvaluator;
import cn.hutool.core.util.RandomUtil;

public class Main {
    public static void main(String[] args) {
        ExecutorRuner executorRuner = new ExecutorRuner.Builder()
                .setGAParameters(50, 0.5f, 0.2f)
                .setAcoParameters(100, 50)
                .build();
        //随机生成 serviceCount 个服务 的 特征数据
        // 特征： 运行时间 优先级
        int serviceCount = 200;
        int featureCount = 2;
        double[][] data = new double[serviceCount][featureCount];
        for (int i = 0; i < serviceCount; i++) {
            for (int j = 0; j < featureCount; j++) {
                data[i][j] = RandomUtil.randomDouble(1, 10);
            }
        }
        int[] res = executorRuner.run(data, new DefaultEvaluator());
        System.out.println("ACO算法结果");
        for (int i = 0; i < res.length; i++) {
            String str = i != res.length - 1 ? "->" : "\n";
            System.out.print(res[i] + str);
        }
    }
}

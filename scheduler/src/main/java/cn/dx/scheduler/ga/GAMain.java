package cn.dx.scheduler.ga;

public class GAMain {

    public static void main(String[] args) {
        SequenceGA ga = new SequenceGA(10, 300, 1, 0.015);
        ga.initGA(2, -2, 2, new TargetEvaluation() {
            @Override
            public double eval(int[] seq, double[][] data) {
                //TODO 根据时间序列 和 特征信息 计算适应度， 适应度越大越好
                return Math.cos(seq[0]) + Math.sin(seq[1]);
            }
        });
        //TODO 特征数据
        double[][] data = new double[1000][10];
        ga.start(data);
        int[] bestIndividual = ga.getBestIndividual();
        for (int item : bestIndividual)
            System.out.print(item + " ");
    }

}

package cn.dx.scheduler.ga;

/**
 * 时间序列评估函数
 */
public interface TargetEvaluation {
    /**
     * @param seq  执行序列
     * @param data 序列的所有特征
     * @return
     */
    double eval(int[] seq, double[][] data);
}

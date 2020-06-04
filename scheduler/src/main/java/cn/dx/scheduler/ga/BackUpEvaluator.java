package cn.dx.scheduler.ga;

public class BackUpEvaluator implements TargetEvaluation {
    @Override
    public double eval(int[] seq, double[][] data) {
        double restCPU = 0.0;
        double restMem = 0.0;
        double currentCPUSum = 0.0;
        double currentMemSum = 0.0;
        for (int i = 0;i<seq.length;i++){
            if(data[seq[i]][0]+currentCPUSum>=1.0){
                restCPU += 1.0-currentCPUSum;
                currentCPUSum = 0.0;
            }
            currentCPUSum += data[seq[i]][0];
            if(data[seq[i]][1]+currentMemSum>=1.0){
                restMem += 1.0 - currentMemSum;
                currentMemSum = 0.0;
            }
            currentMemSum += data[seq[i]][1];
        }
        //遇到cpu和内存同时占满的情况少见，特殊处理,适应度设为极高
        if(restCPU==0&&restMem==0){
            double currentFitness = 1/0.000001;
            return currentFitness;
        }
        //剩余的计算资源和存储资源少，适应度越高，取倒数
        else{
            double currentFitness = 1.0/restCPU + 1.0/restMem;
            return currentFitness;
        }
    }
}

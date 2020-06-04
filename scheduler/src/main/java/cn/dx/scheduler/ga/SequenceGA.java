package cn.dx.scheduler.ga;

import cn.hutool.core.util.RandomUtil;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;

/**
 * 优化时间序列的遗传算法
 */
@NoArgsConstructor
public class SequenceGA {


    /**
     * 时序序列的特征数据
     */
    private double[][] data;


    // GA相关

    /**
     * 种群规模
     */
    private int scale;
    /**
     * 最大迭代次数
     */
    private int maxGen;
    /**
     * 当前迭代次数
     */
    private int curGen;

    /**
     * 交叉概率
     */
    private double rateOfCross;
    /**
     * 变异概率
     */
    private double rateOfMutate;

    /**
     * 染色体长度
     */
    private int dnaLength;
    /**
     * 初始种群，父代种群，行数表示种群规模，一行代表一个个体，即染色体，列表示染色体基因片段
     */
    public int[][] oldPopulation;

    /**
     * 新的种群，子代种群
     */
    private int[][] newPopulation;

    /**
     * 种群适应度，表示种群中各个个体的适应度
     */
    private double[] fitness;

    private Random random;

    /**
     * 定义域范围
     */
    private double minThreshold;
    private double maxThreshold;

    /**
     * 累积矩阵
     */
    private double[] accumlateMatrix;

    /**
     * 评估类
     */
    private TargetEvaluation eval;


    public SequenceGA(int scale, int maxGen, double rateOfCross, double rateOfMutate) {
        this.scale = scale;
        this.maxGen = maxGen;
        this.rateOfCross = rateOfCross;
        this.rateOfMutate = rateOfMutate;
    }

    /**
     * 初始化
     *
     * @param dnaLength    染色体长度  == 当前任务序列的任务个数
     * @param minThreshold
     * @param maxThreshold
     */
    public void initGA(int dnaLength, double minThreshold, double maxThreshold, TargetEvaluation eval) {

        this.oldPopulation = new int[scale][dnaLength];
        this.newPopulation = new int[scale][dnaLength];
        this.fitness = new double[scale];
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.random = new Random(System.currentTimeMillis());
        this.dnaLength = dnaLength;
        this.eval = eval;
        this.accumlateMatrix = new double[scale];
        this.curGen = 0;

        int[] tmpInt = new int[dnaLength];
        for (int j = 0; j < dnaLength; j++) {
            tmpInt[j] = j;
        }

        //TODO 初始化种群
        for (int i = 0; i < scale; i++) {
            int[] individual = tmpInt.clone();
            shuttle(individual);
            this.oldPopulation[i] = individual;
            this.newPopulation[i] = individual.clone();
        }

//        //计算初始种群的适应度和累积矩阵
//        calFitness();
//        calAccumulateMatrix();
    }

    /**
     * 打乱数组
     *
     * @param arr 原始数组
     */
    public void shuttle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int rmNum = RandomUtil.randomInt(i, arr.length);
            int temp = arr[i];
            arr[i] = arr[rmNum];
            arr[rmNum] = temp;
        }
    }


    /**
     * 计算适应度
     */
    private void calFitness() {
        for (int i = 0; i < scale; i++) {
            this.fitness[i] = evaluate(this.oldPopulation[i]);
        }
    }

    /**
     * 计算累积矩阵
     */
    private void calAccumulateMatrix() {
        double sumEval = 0.0;
        for (int i = 0; i < scale; i++) {
            double curFitness = this.fitness[i];
            sumEval += curFitness;
            if (i == 0) {
                this.accumlateMatrix[i] = curFitness;
            } else {
                this.accumlateMatrix[i] = this.accumlateMatrix[i - 1] + curFitness;
            }
        }
        for (int j = 0; j < scale; j++) {
            this.accumlateMatrix[j] /= sumEval;
        }
    }

    /**
     * 开始进化
     *
     * @param data 特征数据
     */
    public void start(double[][] data) {
        this.data = data;
        //计算初始种群的适应度和累积矩阵
        calFitness();
        calAccumulateMatrix();
        for (curGen = 0; curGen < maxGen; curGen++) {

            //达到最小误差 退出
//            if (Math.abs(this.fitness[scale-2]-this.fitness[scale-1]) < 10E-5){
//                break;
//            }

            // 每次迭代 保存最优个体
            double tmpFitness = this.fitness[0];
            int bestIndividualIndex = 0;
            for (int i = 1; i < this.scale; i++) {
                if (this.fitness[i] > tmpFitness) {
                    tmpFitness = this.fitness[i];
                    bestIndividualIndex = i;
                }
            }
            newPopulation[0] = this.oldPopulation[bestIndividualIndex];

            //选择剩下scale-1个体
            for (int i = 1; i < scale; i++) {
                int A = choose();
                int B;
                do {
                    B = choose();
                } while (A == B);
                //交叉
                crossover(A, B);
            }

            //对于每个位置变异
            for (int j = 0; j < scale; j++) {
                if (this.random.nextDouble() < rateOfMutate) {
                    mutate(j);
                }
            }

            reCalculate();
            System.out.println(String.format("curGen: %d, the best fitness is %.6f", curGen, Arrays.stream(this.fitness).max().getAsDouble()));
        }
    }

    /**
     * 重新计算参数
     */
    private void reCalculate() {
        for (int i = 0; i < scale; i++) {
            this.oldPopulation[i] = this.newPopulation[i];
        }
        calFitness();
        calAccumulateMatrix();
    }

    /**
     * 选择
     *
     * @return 返回选择个体的index
     */
    public int choose() {
        //根据累积矩阵选择
        double rate = RandomUtil.randomDouble();
        for (int i = 0; i < this.scale - 1; i++) {
            if (i == 0) {
                if (rate < this.accumlateMatrix[i]) {
                    return 0;
                }
            }
            if (rate > this.accumlateMatrix[i] && rate < this.accumlateMatrix[i + 1]) {
                return i + 1;
            }
        }
        return this.scale - 1;
    }

    /**
     * 交叉
     * 顺序交叉
     *
     * @param A 父母
     * @param B 父母
     */
    public void crossover(int A, int B) {
        // 如果不满足交叉概率，直接将A插入新的种群
        int rand1 = RandomUtil.randomInt(0, this.dnaLength);
        int rand2 = RandomUtil.randomInt(0, this.dnaLength);

        int[] popA = this.oldPopulation[A].clone();
        int[] popB = this.oldPopulation[B].clone();

        int start = Math.min(rand1, rand2);
        int end = Math.max(rand1, rand2);

        for (int i = start; i < end; i++) {
            for (int j = 0; j < dnaLength; j++) {
                if (popA[i] == popB[j]) {
                    int tmpValue = popA[j];
                    popA[j] = popA[i];
                    popA[i] = tmpValue;
                }
                if (popB[i] == popA[j]) {
                    int tmpValue = popB[j];
                    popB[j] = popB[i];
                    popB[i] = tmpValue;
                }

            }
        }


//        for (int i = start; i < end; i++) {
//            int tmpValue = popA[i];
//            popA[i] = popB[i];
//            popB[i] = tmpValue;
//        }

        this.newPopulation[A] = popA;
        this.newPopulation[B] = popB;
    }

    /**
     * 逆序变异
     *
     * @param index 个体的位置
     */
    public void mutate(int index) {
        int rand1 = RandomUtil.randomInt(0, this.dnaLength);
        int rand2 = RandomUtil.randomInt(0, this.dnaLength);
        int start = Math.min(rand1, rand2);
        int end = Math.max(rand1, rand2);
        int[] copyPop = this.newPopulation[index];
        for (int i = start; i < (start + end) / 2; i++) {
            int tmpValue = copyPop[i];
            copyPop[i] = copyPop[end - i];
            copyPop[end - i] = tmpValue;
        }
    }

    /**
     * 评估
     */
    public double evaluate(int[] individual) {
        return this.eval.eval(individual, this.data);
    }

    /**
     * 获取最优个体
     *
     * @return
     */
    public int[] getBestIndividual() {
        reCalculate();
        int bestFitnessIdx = 0;
        for (int i = 1; i < scale; i++) {
            if (this.fitness[i] > this.fitness[bestFitnessIdx]) {
                bestFitnessIdx = i;
            }
        }
        return this.oldPopulation[bestFitnessIdx];
    }
}

package cn.dx.optimizer.ga;

import java.util.Arrays;
import java.util.Random;

public class GA {

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
    public double[][] oldPopulation;

    /**
     * 新的种群，子代种群
     */
    private double[][] newPopulation;

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
    private IEvalFunc eval;


    public GA(int scale, int maxGen, double rateOfCross, double rateOfMutate) {
        this.scale = scale;
        this.maxGen = maxGen;
        this.rateOfCross = rateOfCross;
        this.rateOfMutate = rateOfMutate;
    }

    /**
     * 初始化
     *
     * @param dnaLength
     * @param minThreshold
     * @param maxThreshold
     */
    public void init(int dnaLength, double minThreshold, double maxThreshold, IEvalFunc eval) {

        this.oldPopulation = new double[scale][dnaLength];
        this.newPopulation = new double[scale][dnaLength];
        this.fitness = new double[scale];
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.random = new Random(System.currentTimeMillis());
        this.dnaLength = dnaLength;
        this.eval = eval;
        this.accumlateMatrix = new double[scale];
        this.curGen = 0;


        // 初始化种群
        for (int i = 0; i < scale; i++) {
            double[] individual = new double[dnaLength];
            for (int j = 0; j < dnaLength; j++) {
                individual[j] = minThreshold + (maxThreshold - minThreshold) * getRandom();
            }
            this.oldPopulation[i] = individual;
        }

        //计算初始种群的适应度和累积矩阵
        calFitness();
        calAccumulateMatrix();
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
        //计算初始累积概率
        double sumEval = 0.0;
//        double minFitness = Arrays.stream(this.fitness).min().getAsDouble();

        for (int i = 0; i < scale; i++) {
//            double curFitness = this.fitness[i] - minFitness;
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
     */
    public void start() {

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
                crossover(A, B, i);
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
        double rate = getRandom();
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
     *
     * @param A     父母
     * @param B     父母
     * @param index 新种群的位置
     */
    public void crossover(int A, int B, int index) {
        // 如果不满足交叉概率，直接将A插入新的种群
        if (this.random.nextDouble() > this.rateOfCross) {
            this.newPopulation[index] = this.oldPopulation[A];
            return;
        }
        double[] newIndividual = new double[this.dnaLength];
        for (int i = 0; i < dnaLength; i++) {
            newIndividual[i] = 0.5 * this.oldPopulation[A][i] + (1 - 0.5) * this.oldPopulation[B][i];
        }
        this.newPopulation[index] = newIndividual;
    }

    /**
     * 变异
     *
     * @param index 个体的位置
     */
    public void mutate(int index) {
        double stepDistance = 0.05;
        for (int i = 0; i < dnaLength; i++) {
            int dir = this.random.nextInt() % 2 == 0 ? -1 : 1;
            this.newPopulation[index][i] = this.newPopulation[index][i] + stepDistance * dir;
        }
    }

    /**
     * 评估
     */
    public double evaluate(double[] individual) {
//        return -(Math.pow(individual[0], 2) + 2 * individual[0] + 1);
        return this.eval.eval(individual);
    }

    public double getRandom() {
        return this.random.nextDouble();
    }

    /**
     * 获取最优个体
     *
     * @return
     */
    public double[] getBestIndividual() {
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

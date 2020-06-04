package cn.dx.scheduler.aco;


public class ACO {
    /**
     * 蚂蚁对象数组
     */
    Ant[] ants;
    /**
     * 蚂蚁的数量
     */
    int antCount;
    /**
     * 服务两两之间的评估结果
     */
    double[][] distance;
    /**
     * 服务两两之间的信息素 公式中的tao
     */
    double[][] pheromone;
    /**
     * 服务的数量
     */
    int serviceCount;
    /**
     * 最优的路线
     */
    int[] bestTour;

    /**
     * 当前最优长度
     */
    double bestLength;

    public ACO(int serviceCount, double[][] distance) {
        this.serviceCount = serviceCount;
        this.distance = distance;
    }

    /**
     * 初始化蚁群
     *
     * @param antCount 蚂蚁的数量
     */
//    public void init(int antCount, int[] gaResultIndividual) {
    public void init(int antCount, int[][] gaResultIndividual) {
        this.antCount = antCount;
        ants = new Ant[antCount];
        pheromone = new double[serviceCount][serviceCount];

        for (int i = 0; i < serviceCount; i++) {
            for (int j = 0; j < serviceCount; j++) {
                pheromone[i][j] = 1;
            }
        }
        for (int i = 0; i < gaResultIndividual.length; i++) {
            int[] itemSeq = gaResultIndividual[i];
            for (int j = 0; j < itemSeq.length - 1; j++) {
                pheromone[itemSeq[j]][itemSeq[j + 1]] *= 1.005;
            }
        }

        bestLength = Double.MAX_VALUE;
        bestTour = new int[serviceCount];
        for (int i = 0; i < antCount; i++) {
            ants[i] = new Ant();
            ants[i].init(serviceCount);
        }
    }

    /**
     * 蚁群算法的运行入口
     *
     * @param maxgen 运行最大的代数
     */
    public void run(int maxgen) {
        for (int gen = 0; gen < maxgen; gen++) {
            //每一只蚂蚁的移动过程
            for (int i = 0; i < antCount; i++) {
                //对该蚂蚁进行城市路线选择
                for (int j = 1; j < serviceCount; j++) {
//                for (int j = 0; j < serviceCount -1; j++) {
                    ants[i].selectNextService(j, pheromone, distance);
                }
                //计算该蚂蚁爬过的路线总长度
                ants[i].calcTourLength(distance);
                //判断是否为最优路线
                if (ants[i].getLength() < bestLength) {
                    //保存最优代
                    bestLength = ants[i].getLength();
                    bestTour = ants[i].getTour();
                }
            }
            //更新信息素
            updatePheromone();
            //蚂蚁重新初始化
            for (int i = 0; i < antCount; i++) {
                ants[i].init(serviceCount);
            }
        }
        //System.out.println("end");
    }

    /**
     * 更新信息素,使用ant-cycle模型 <br/>
     * 公式1: T_ij(t+1) = (1-r)*T_ij(t) + delta_T_ij(t) <br/>
     * 公式2: delta_T_ij(t) = Q/L_k Q为常数，L_k为蚂蚁走过的总长度
     */
    private void updatePheromone() {
        double rou = 0.5;
        for (int i = 0; i < serviceCount; i++) {
            for (int j = 0; j < serviceCount; j++) {
                pheromone[i][j] *= (1 - rou);
            }
        }
        for (int i = 0; i < antCount; i++) {
            for (int j = 0; j < serviceCount - 1; j++) {
                int curId = ants[i].getTour()[j];
                int nextId = ants[i].getTour()[j + 1];
                pheromone[curId][nextId] += 1.0 / ants[i].getLength();
            }
        }
    }

    public int[] getBestTour() {
        return bestTour;
    }
}

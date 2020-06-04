package cn.dx.scheduler.aco;

import java.util.Random;

class Ant {
    /**
     * 蚂蚁的路径
     */
    private int[] tour;

    /**
     * 存储是否执行过某个服务，1代表访问过
     */
    private int[] services;

    /**
     * 蚂蚁当前走过的距离
     */
    private double length;

    /**
     * 城市个数
     */
    private int count;

    /**
     * 公式中得参数alpha
     */
    private double alpha = 1.0;
    /**
     * 公式中得参数beta
     */
    private double beta = 2.0;

    /**
     * 获得蚂蚁当前的路线
     *
     * @return
     */
    public int[] getTour() {
        return tour;
    }

    /**
     * 获得蚂蚁当前的长度
     *
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     * 初始化蚂蚁的起始路径
     *
     * @param count 城市的个数
     */
    public void init(int count) {
        this.count = count;
        services = new int[count];
//		tour = new int[count + 1];
        tour = new int[count];
        for (int i = 0; i < count; i++) {
            services[i] = 0;
        }
        int random = new Random(System.currentTimeMillis()).nextInt(count);
        services[random] = 1;
        tour[0] = random;
    }

    /**
     * 通过信息素和距离计算轮盘赌注概率，选择下一个城市
     *
     * @param index
     * @param pheromone
     * @param distance
     */
    public void selectNextService(int index, double[][] pheromone, double[][] distance) {
		int current = tour[index - 1];
        double[] p = new double[count];
        double sum = 0.0;//信息素概率总和
        //公式中得分母部分
        for (int i = 0; i < count; i++) {
            if (services[i] == 0) {
                sum += Math.pow(pheromone[current][i], this.alpha)
                        * (Math.pow(1.0 / distance[current][i], this.beta));
            }
        }
        //公式中的分子部分
        for (int i = 0; i < count; i++) {
            if (services[i] == 1) {
                p[i] = 0.0;
            } else {
                p[i] = Math.pow(pheromone[current][i], this.alpha)
                        * (Math.pow(1.0 / distance[current][i], this.beta)) / sum;
            }
        }
        int select = getRandomCity(p);
        tour[index] = select;
        services[select] = 1;
    }

    /**
     * 使用轮盘赌注选择城市
     *
     * @param p
     * @return
     */
    private int getRandomCity(double[] p) {
        double selectP = new Random(System.currentTimeMillis()).nextDouble();
        double sumSel = 0.0;
        for (int i = 0; i < count; i++) {
            sumSel += p[i];
            if (sumSel > selectP)
                return i;
        }
        return -1;
    }

    /**
     * TODO 修改
     * 计算蚂蚁当前走过的距离总和
     *
     * @param distance
     */
    public void calcTourLength(double[][] distance) {
        length = 0;
//        tour[count] = tour[0];
        for (int i = 0; i < count-1; i++) {
            length += distance[tour[i]][tour[i + 1]];
        }
    }
}

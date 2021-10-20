package cn.dx.dfs.datanode;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class DataNode {

    /**
     * 是否还在运行
     */
    private volatile Boolean shouldRun;

    /**
     * 负责和一组NameNode通信的组件
     */
    private NameNodeOfferService offerService;

    private void initilalize() {
        this.shouldRun = true;
        this.offerService = new NameNodeOfferService();
        this.offerService.start();
    }

    private void run() {
        try {
            while (shouldRun) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataNode dataNode = new DataNode();
        dataNode.initilalize();
        dataNode.run();
    }
}

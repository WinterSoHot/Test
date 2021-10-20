package cn.dx.dfs.namenode;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class NameNode {

    private FSNameSystem nameSystem;
    private DataNodeManager dataNodeManager;
    private NameNodeRpcServer rpcServer;


    private void initialize() throws Exception {
        this.nameSystem = new FSNameSystem();
        this.dataNodeManager = new DataNodeManager();
        this.rpcServer = new NameNodeRpcServer(nameSystem, dataNodeManager);
    }

    private void start() throws Exception {
        this.rpcServer.start();
        this.rpcServer.blockUntilShutdown();
    }

    public static void main(String[] args) throws Exception {
        NameNode nameNode = new NameNode();
        nameNode.initialize();
        nameNode.start();
    }
}

package cn.dx.dfs.namenode;

import cn.dx.dfs.namenode.rpc.service.NameNodeServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class NameNodeRpcServer {
    private static final int DEFAULT_PORT = 50070;

    private Server server = null;

    private FSNameSystem nameSystem;

    private DataNodeManager dataNodeManager;

    public NameNodeRpcServer(FSNameSystem nameSystem, DataNodeManager dataNodeManager) {
        this.nameSystem = nameSystem;
        this.dataNodeManager = dataNodeManager;
    }

    public void start() throws IOException {
        server = ServerBuilder
                .forPort(DEFAULT_PORT)
                .addService(NameNodeServiceGrpc.bindService(new NameNodeServiceImpl(nameSystem, dataNodeManager)))
                .build().start();

        System.out.println("NameNodeRpcServer启动，监听端口号：" + DEFAULT_PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(NameNodeRpcServer.this::stop));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}

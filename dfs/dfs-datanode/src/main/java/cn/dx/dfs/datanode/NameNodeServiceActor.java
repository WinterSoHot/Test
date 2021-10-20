package cn.dx.dfs.datanode;

import cn.dx.dfs.namenode.rpc.model.HeartbeatRequest;
import cn.dx.dfs.namenode.rpc.model.HeartbeatResponse;
import cn.dx.dfs.namenode.rpc.model.RegisterRequest;
import cn.dx.dfs.namenode.rpc.model.RegisterResponse;
import cn.dx.dfs.namenode.rpc.service.NameNodeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class NameNodeServiceActor {

    private static final String NAME_NODE_HOSTNAME = "localhost";
    private static final Integer NAME_NODE_PORT = 50070;

    private NameNodeServiceGrpc.NameNodeServiceBlockingStub nameNodeStub;

    public NameNodeServiceActor() {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress(NAME_NODE_HOSTNAME, NAME_NODE_PORT)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        this.nameNodeStub = NameNodeServiceGrpc.newBlockingStub(channel);
    }

    public void register() throws Exception {
        Thread registerThread = new RegisterThread();
        registerThread.start();
        registerThread.join();
    }

    public void startHeartBeat() {
        new HeartBeatThread().start();
    }

    class RegisterThread extends Thread {
        @Override
        public void run() {
            try {

                System.out.println("发送RPC请求到NameNode进行注册........");
                String ip = "127.0.0.1";
                String hostName = "dfs-data-01";
                RegisterRequest request = RegisterRequest.newBuilder().
                        setIp(ip)
                        .setHostname(hostName).build();
                RegisterResponse reponse = nameNodeStub.register(request);
                System.out.println("接受到NameNode返回的注册响应；" + reponse.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class HeartBeatThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("发送RPC请求到NameNode进行心跳........");
                    String ip = "127.0.0.1";
                    String hostName = "dfs-data-01";
                    HeartbeatRequest request = HeartbeatRequest.newBuilder().
                            setIp(ip)
                            .setHostname(hostName).build();
                    HeartbeatResponse reponse = nameNodeStub.heartbeat(request);
                    System.out.println("接受到NameNode返回的心跳响应；" + reponse.getStatus());
                    Thread.sleep(30 * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

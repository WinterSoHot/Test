package cn.dx.dfs.namenode;

import cn.dx.dfs.namenode.rpc.model.HeartbeatRequest;
import cn.dx.dfs.namenode.rpc.model.HeartbeatResponse;
import cn.dx.dfs.namenode.rpc.model.RegisterRequest;
import cn.dx.dfs.namenode.rpc.model.RegisterResponse;
import cn.dx.dfs.namenode.rpc.service.NameNodeServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class NameNodeServiceImpl implements NameNodeServiceGrpc.NameNodeService {

    public static final Integer STATUS_SUCCESS = 1;
    public static final Integer STATUS_FAILURE = 2;

    /**
     * 负责管理元数据的核心组件
     */
    private FSNameSystem nameSystem;

    /**
     * 负责管理集群中所有的datanode组件
     */
    private DataNodeManager dataNodeManager;

    public NameNodeServiceImpl(FSNameSystem nameSystem, DataNodeManager dataNodeManager) {
        this.nameSystem = nameSystem;
        this.dataNodeManager = dataNodeManager;
    }


    @Override
    public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
        dataNodeManager.register(request.getIp(), request.getHostname());
        RegisterResponse response = RegisterResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void heartbeat(HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
        dataNodeManager.heartbeat(request.getIp(), request.getHostname());

        HeartbeatResponse response = HeartbeatResponse.newBuilder()
                .setStatus(STATUS_SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

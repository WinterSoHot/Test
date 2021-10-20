package cn.dx.dfs.datanode;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class NameNodeOfferService {

    /**
     * 负责和NameNode主节点进行通信的ServiceActor组件
     */
    private NameNodeServiceActor serviceActor;

    /**
     * dataNode上保存的ServiceActor列表
     */
    private CopyOnWriteArrayList<NameNodeServiceActor> serviceActors;

    public NameNodeOfferService() {
        this.serviceActor = new NameNodeServiceActor();
    }

    public void start() {
        register();
        startHeartBeat();
    }

    private void register() {
        try {

            this.serviceActor.register();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startHeartBeat() {
        this.serviceActor.startHeartBeat();
    }

    public void shutdown(NameNodeServiceActor serviceActor) {
        this.serviceActors.remove(serviceActor);
    }

    public void iterateServiceActors() {
        Iterator<NameNodeServiceActor> iterator = serviceActors.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

}

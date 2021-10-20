package cn.dx.dfs.namenode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class DataNodeManager {

    private Map<String, DataNodeInfo> dataNodes = new ConcurrentHashMap<>();

    public DataNodeManager() {
        new DataNodeAliveMonitor().start();
    }

    /**
     * datanode进行注册
     *
     * @param ip
     * @param hostname
     * @return
     * @throws Exception
     */
    public Boolean register(String ip, String hostname) {
        DataNodeInfo dataNode = new DataNodeInfo(ip, hostname);
        dataNodes.put(ip + "-" + hostname, dataNode);
        System.out.println("DataNode注册： ip = " + ip + ", hostname =" + hostname);
        return true;
    }

    /**
     * datanode进行心跳
     *
     * @param ip
     * @param hostname
     * @return
     * @throws Exception
     */
    public Boolean heartbeat(String ip, String hostname) {
        DataNodeInfo datanode = dataNodes.get(ip + "-" + hostname);
        datanode.setLatestHeartBeatTime(System.currentTimeMillis());
        System.out.println("DataNode发送心跳：ip=" + ip + ", hostname=" + hostname);
        return true;
    }

    class DataNodeAliveMonitor extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    List<String> toRemoveDataNodes = new ArrayList<>();
                    Iterator<DataNodeInfo> dataNodeIterator = dataNodes.values().iterator();
                    DataNodeInfo dataNode = null;
                    while (dataNodeIterator.hasNext()) {
                        dataNode = dataNodeIterator.next();
                        if (System.currentTimeMillis() - dataNode.getLatestHeartBeatTime() > 90 * 1000) {
                            toRemoveDataNodes.add(dataNode.getIp() + "-" + dataNode.getHostName());
                        }
                    }
                    if (!toRemoveDataNodes.isEmpty()) {
                        for (String toRemoveDataNode : toRemoveDataNodes) {
                            dataNodes.remove(toRemoveDataNode);
                        }
                    }
                    Thread.sleep(30 * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

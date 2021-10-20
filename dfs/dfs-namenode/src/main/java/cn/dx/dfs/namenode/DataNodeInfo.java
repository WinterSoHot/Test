package cn.dx.dfs.namenode;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
public class DataNodeInfo {

    private String ip;
    private String hostName;
    private long latestHeartBeatTime = System.currentTimeMillis();

    public DataNodeInfo(String ip, String hostName) {
        this.ip = ip;
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public long getLatestHeartBeatTime() {
        return latestHeartBeatTime;
    }

    public void setLatestHeartBeatTime(long latestHeartBeatTime) {
        this.latestHeartBeatTime = latestHeartBeatTime;
    }
}

package cn.dx.dfs.namenode;


import lombok.Data;

/**
 * @author gudongxian
 * @date 2021/10/20
 */
@Data
public class DataNodeInfo {

    private String ip;
    private String hostName;
    private long latestHeartBeatTime = System.currentTimeMillis();

    public DataNodeInfo(String ip, String hostName) {
        this.ip = ip;
        this.hostName = hostName;
    }
}

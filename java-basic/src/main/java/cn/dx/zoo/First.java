package cn.dx.zoo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/12
 */
public class First {
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory
                .newClient("127.0.0.1:2181",
                        retryPolicy);
        client.start();
        try {
            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .forPath("/base/path", "init".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package cn.dx.java.mq.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 1、Producer端发送同步消息
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/21
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("test_unique_name");
        producer.setNamesrvAddr(RocketMQConstraint.NAME_SERVER);
        producer.start();
        producer.createTopic("1", "TopicTest", 3);
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest", "TAGA", ("Hello, RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}

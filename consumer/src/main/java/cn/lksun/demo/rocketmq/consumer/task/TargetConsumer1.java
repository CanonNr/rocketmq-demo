package cn.lksun.demo.rocketmq.consumer.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "target",
        consumerGroup = "websocket-server"
)
public class TargetConsumer1 implements RocketMQListener<MessageExt> , RocketMQPushConsumerLifecycleListener {
    public void onMessage(MessageExt ext) {
        String msg = new String(ext.getBody());
        log.info("{} - {} - {}","target","websocket-server",msg);
    }

    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueStrategy() {
            /**
             * 自定义MessageQueue分配
             * @param s 消费组
             * @param s1 服务端的配置
             * @param list  MessageQueue 个数
             * @param list1 消费者客户端数量
             */
            public List<MessageQueue> allocate(String s, String s1, List<MessageQueue> list, List<String> list1) {
                System.out.println("---------------------");
                System.out.println(s);
                System.out.println(s1);
                System.out.println(list.size());
                System.out.println(list1.size());
                System.out.println(list1.get(0));
                System.out.println(list1.get(1));
                System.out.println("---------------------");
                return null;
            }

            public String getName() {
                return "test";
            }
        });


    }
}

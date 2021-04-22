package cn.lksun.demo.rocketmq.consumer.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RocketMQMessageListener(
        topic = "TEST_02",
        consumerGroup = "my999"
)
public class RocketMqConsumer implements RocketMQListener<MessageExt>,RocketMQPushConsumerLifecycleListener {
    public void onMessage(MessageExt  ext) {
        String s = new String(ext.getBody());

        if (s.equals("error")){
            System.out.println(10/0);
        }


    }


    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置重试次数进入死信队列,默认16
        consumer.setMaxReconsumeTimes(2);
    }

}

package cn.lksun.demo.rocketmq.consumer.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
//@RocketMQMessageListener(
//        topic = "test-topic2",
//        consumerGroup = "my-group3"
//)
public class TransactionConsumer2 implements RocketMQListener<MessageExt> {
    public void onMessage(MessageExt  ext) {
        String s = new String(ext.getBody());
        log.info("test-topic2 message:{}",s);
    }
}

package cn.lksun.demo.rocketmq.consumer.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "%DLQ%my999",
        consumerGroup = "my888"
)
public class TransactionConsumer1 implements RocketMQListener<MessageExt> {
    public void onMessage(MessageExt  ext) {
        String s = new String(ext.getBody());
        log.info("DLQ-my999 message:{}",s);
    }
}

package cn.lksun.demo.rocketmq.consumer.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "target",
        consumerGroup = "target-service"
)
public class TargetConsumer2 implements RocketMQListener<MessageExt> {
    public void onMessage(MessageExt ext) {
        String msg = new String(ext.getBody());
        log.info("{} - {} - {}","target","target-service",msg);
    }
}

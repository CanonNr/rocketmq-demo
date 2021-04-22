package cn.lksun.demo.rocketmq.consumer.task;

import lombok.SneakyThrows;
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
        topic = "test-topic",
        consumerGroup = "my-group3"
)
public class TransactionConsumer2 implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    @SneakyThrows
    public void onMessage(MessageExt  ext) {
        // 此处睡五秒,假设这是在执行业余代码...
        Thread.sleep(5000);
        String s = new String(ext.getBody());
        log.info("test-topic2 message:{}",s);
    }

    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置消费者最大线程数5
        consumer.setConsumeThreadMax(5);
        // 设置消费者最小线程数5
        consumer.setConsumeThreadMin(5);
        // 设置成这样的效果就是:在很短的时间内生成大量任务,但是一次只消费五个
        // 所以在控制台,会每隔五秒钟输出五条记录
        // 削峰
    }
}

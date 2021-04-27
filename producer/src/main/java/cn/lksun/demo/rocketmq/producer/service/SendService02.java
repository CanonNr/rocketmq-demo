package cn.lksun.demo.rocketmq.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SendService02 {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    public boolean send(String s){
        Message<String> message = MessageBuilder.withPayload(s).build();
        rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
            /**
             * @param list MessageQueue列表,可以通过size取到大小,通过rm console 可以设置
             * @param message 消息本体 用不到
             * @param o hash key
             * @return 分配到哪一个 MessageQueue
             */
            @Override
            public MessageQueue select(List<MessageQueue> list, org.apache.rocketmq.common.message.Message message, Object o) {
                int key=o.hashCode();
                int size = list.size();
                int index = key%size;
                return list.get(index);
            }
        });
        // 顺序消息,并实现自定义queue分区
        rocketMQTemplate.syncSendOrderly("target", message, "123");
        return true;
    }


}

package cn.lksun.demo.rocketmq.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SendService02 {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    public boolean send(String s){
        Message<String> message = MessageBuilder.withPayload(s).build();
        rocketMQTemplate.sendMessageInTransaction("target", message, null);
        return true;
    }


}

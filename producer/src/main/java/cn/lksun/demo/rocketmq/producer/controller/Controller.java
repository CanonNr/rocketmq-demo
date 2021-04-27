package cn.lksun.demo.rocketmq.producer.controller;

//import cn.lksun.demo.rocketmq.producer.service.SendService01;
import cn.lksun.demo.rocketmq.producer.service.SendService02;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Slf4j
@RestController
public class Controller {

    @Resource
    RocketMQTemplate rocketMQTemplate;

//    @Resource
//    SendService01 sendService01;

    @Resource
    SendService02 sendService02;

    @GetMapping("/0")
    public String test0(String message){
        // 同步
//        rocketMQTemplate.convertAndSend("TEST_01",message);
        Message<?> msg = MessageBuilder.withPayload(message).
                setHeader(MessageConst.PROPERTY_MAX_RECONSUME_TIMES, "2").
                build();
        rocketMQTemplate.syncSend("TEST_02",msg);
        log.info("Success...");

        // 延时消息
//        rocketMQTemplate.syncSend("TEST_01",message,String.class,1000,3);

//        rocketMQTemplate.asyncSend("TEST_01", message, new SendCallback() {
//            public void onSuccess(SendResult sendResult) {
//                log.info("send Success");
//            }
//
//            public void onException(Throwable throwable) {
//                log.error("send error:{}",throwable.getMessage());
//            }
//        });
        return "ok";
    }

    @GetMapping("/1")
    public String test1(String message) throws MQClientException {
//
//        if (sendService01.send(message)){
            return "ok";
//        }
//        return "error";
    }
    @GetMapping("/1-1")
    public String test11(String message) throws MQClientException {

        if (sendService02.send(message)){
            return "ok";
        }
        return "error";
    }



}

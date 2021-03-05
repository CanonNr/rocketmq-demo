package cn.lksun.demo.rocketmq.producer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@Slf4j
@RocketMQTransactionListener
class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // ... local transaction process, return rollback, commit or unknown
        String body = new String((byte[]) msg.getPayload());
        log.info("exec 1 :{}",body);

        if (body.equals("10086")){
            return RocketMQLocalTransactionState.ROLLBACK;
        }else if(body.equals("666")){
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // ... check transaction status and return bollback, commit or unknown
        String body = new String((byte[]) msg.getPayload());
        log.info("check 1 :{}",body);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
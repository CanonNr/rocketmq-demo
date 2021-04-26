//package cn.lksun.demo.rocketmq.producer.listener;
//
//import org.apache.rocketmq.client.producer.LocalTransactionState;
//import org.apache.rocketmq.client.producer.TransactionListener;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.stereotype.Component;
//
//
//
//public class TransactionService implements TransactionListener {
//    @Override
//    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
//        String msg = new String(message.getBody());
//        System.out.println(msg);
//        if (msg.equals("888")){
//            return LocalTransactionState.COMMIT_MESSAGE;
//        }else if (msg.equals("000")){
//            System.out.println("-000");
//            return LocalTransactionState.UNKNOW;
//        }else{
//            return LocalTransactionState.ROLLBACK_MESSAGE;
//        }
//    }
//
//    @Override
//    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
//        System.out.println("hahahha");
//        return LocalTransactionState.COMMIT_MESSAGE;
//    }
//
//}

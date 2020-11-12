package com.shopmall.service;

import com.shopmall.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * rocketMq 本地事务回调监听
 */
@Slf4j
@Service
@RocketMQTransactionListener()
public class TxMessageListener implements RocketMQLocalTransactionListener {
    /**
     * 执行本地事务
     * @param message
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        Order order = (Order)arg;
        String txId = (String) message.getHeaders().get("txId");
        log.info("接收到的是事务消息发送的txId：{}",txId);
        try {
            log.info("接收到的是事务消息发送数据：{}",order);
            //TODO: 这里执行本地事务，保存订单。
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 消息回查
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String txId = (String) message.getHeaders().get("txId");
        log.info("接收到的是事务消息发送的txId：{}",txId);
        //根据txId去查事务日志表，来确定本地事务是否执行成功。txLogDao.queryById()是否存在日志，来判断返回状态(是commit 还是rollback)
        return RocketMQLocalTransactionState.COMMIT;
    }
}

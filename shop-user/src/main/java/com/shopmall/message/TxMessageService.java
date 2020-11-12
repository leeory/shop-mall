package com.shopmall.message;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * rocketMq 监听事务消息
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "tx-shop-user",topic = "tx-topic")
public class TxMessageService implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        log.info("接收到事务消息发送来的数据：{}",msg);
    }
}

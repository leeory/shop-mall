package com.shopmall.message;

import com.shopmall.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 监听 shop-order 微服务发送的消息，注意，
 * consumerGroup(消费者主题)指定当前的服务相关，
 * topic（要消费的主题）必须和发送端的一致，否则接收不到消息
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "shop-user", //消费者组名
                         topic = "order-topic", //消费主题
                          consumeMode = ConsumeMode.ORDERLY, //消费模式：无序（CONCURRENTLY）和有序（ORDERLY），默认同步消费（无序）
                          messageModel = MessageModel.CLUSTERING //消息模式：广播（BROADCASTING）和集群(CLUSTERING),默认广播
)
public class MqMessageService implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("接收到的消息内容是：{}",message);
    }
}

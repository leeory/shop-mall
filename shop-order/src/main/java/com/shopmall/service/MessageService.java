package com.shopmall.service;

import com.alibaba.fastjson.JSON;
import com.shopmall.common.Result;
import com.shopmall.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * rocketMq 一个nameserver管理这多个mqbroker,一个mqbroker里面又有多个queue.
 *
 * RocketMq 消息分为：普通消息和事务消息
 *  提供了三种方式来发送普通消息：可靠同步发送，可靠异步发送，单向发送
 *    1，可靠同步发送：是指消息发送数据后，会收到接收方发回响应之后再发送下一个消息的通讯方式。
 *    2，可靠异步发送：是指发送方发送数据后，不等接收方回应，接着发送下一个消息的通讯方式，发送方通过回调接口接收服务响应，并对响应结果处理。
 *    3，单向发送：是指发送方只负责发送消息，不等服务器响应也没有回调函数出发，即只发送消息不等待应答，适用于某些耗时端短，对可靠性要求不高的场景。
 *
 */
@Slf4j
@Service
public class MessageService {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public Result sendOrder(){
        Order order = new Order(1000L,2000L,"test",5000L,"苹果",5,false,new Date(),new Date());
        rocketMQTemplate.convertAndSend("order-topic", JSON.toJSONString(order));
        return Result.success("发送成功");
    }

    /**
     * 测试发送同步消息
     *
     * 参数一：topic:tag
     * 参数二：消息体
     * 参数三超时时间：单位毫秒
     */
    public Result testSyncSend(){
        rocketMQTemplate.syncSend("order-topic:sync-msg","测试同步消息。。。",10000);
        return Result.success(null);
    }

    /**
     * 测试发送异步消息
     *
     * 参数一：topic:tag
     * 参数二：消息体
     * 参数三：回调函数
     */
    public Result testAsyncSend(){
        rocketMQTemplate.asyncSend("order-topic:async-msg", "测试异步消息。。。", new SendCallback() {
            //消息发送成功的回调
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("testAsyncSend  消息发送成功了，回调了onSuccess方法。。。");
            }
            //异常时回调
            @Override
            public void onException(Throwable throwable) {
                log.error("testAsyncSend  消息发送失败了了，回调了onException方法。。。");
            }
        });

        return Result.success("发送异步消息成功");
    }


    public Result testSendOneWay(){
        rocketMQTemplate.sendOneWay("order-topic:one-way","测试单向消息。。。");
        return Result.success("发送单向消息成功");
    }


    /**
     * RocketMq默认发送的消息会落在不同queue上，当使用顺序发送消息时，消息会落在统一个queue上，保证了消息的顺序性
     * 测试发送顺序消息
     * 下面是console面板里面找到的一个队列里面queue的情况
     * MessageQueue [topic=test-topic-send-orderly, brokerName=leeroy, queueId=3]
     * MessageQueue [topic=test-topic-send-orderly, brokerName=leeroy, queueId=2]
     * MessageQueue [topic=test-topic-send-orderly, brokerName=leeroy, queueId=1]
     * MessageQueue [topic=test-topic-send-orderly, brokerName=leeroy, queueId=0]
     *
     * 最后一个参数：用于mq计算消息落在哪个队列上的，值可以随便给
     *
     */
    public Result testSyncSendOrderly(){
        //rocketMQTemplate.syncSend(); //同步无序发送
        //rocketMQTemplate.syncSendOrderly() //同步有序发送
        //rocketMQTemplate.asyncSend();//异步无序发送
        //rocketMQTemplate.asyncSendOrderly();//异步有序发送
        //rocketMQTemplate.sendOneWay(); //单向无序发送
        //rocketMQTemplate.sendOneWayOrderly();//单向有序发送
        for (int i = 0; i <5 ; i++) {
            rocketMQTemplate.syncSendOrderly("order-topic:one-way-Orderly","测试orderly message。。。","orderly");
        }
        return Result.success("发送有序消息成功");
    }

    /**
     * 测试事务消息
     * RocketMq提供了事务消息，通过事务消息就能达到分布式事务的最终一致性
     *
     *  两个概念：
     *   1.半事务消息：暂不能投递的消息。发送方已成功将消息发送到了mq服务端，但是服务端未收到生产者对该消息的二次确认，
     *             此时该消息被标记为“暂不能投递状态”，处于该种状态的消息被称为“半事务消息”。
     *   2.消息回查：在网络闪断，生产者服务重启等原因，导致事务消息的二次确认丢失，RocketMq服务端通过扫描发现某条消息长期处于“半事务消息”时
     *             需要主动向消息生产者询问该消息的最终状态（commit or rollback），该询问过程即是回查。
     *
     */
    public Result testTxMessage(){
        String txId = UUID.randomUUID().toString();
        Order order = new Order(1000L,2000L,"test",5000L,"苹果",5,false,new Date(),new Date());
        rocketMQTemplate.sendMessageInTransaction("tx-topic", MessageBuilder.withPayload("这是一条事务消息").setHeader("txId",txId).build(),order);
        return Result.success("测试发送事务消息成功");
    }

}

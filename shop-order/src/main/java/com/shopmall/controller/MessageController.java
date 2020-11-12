package com.shopmall.controller;

import com.shopmall.common.Result;
import com.shopmall.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 rocketMq
 */
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("sendOrder")
    public Result sendOrder(){
        return messageService.sendOrder();
    }

    @GetMapping("testSyncSend")
    public Result testSyncSend(){
        return messageService.testSyncSend();
    }

    @GetMapping("testAsyncSend")
    public Result testAsyncSend(){
        return messageService.testAsyncSend();
    }


    @GetMapping("testSendOneWay")
    public Result testSendOneWay(){
        return messageService.testSendOneWay();
    }


    @GetMapping("testSyncSendOrderly")
    public Result testSyncSendOrderly(){
        return messageService.testSyncSendOrderly();
    }

    @GetMapping("testTxMessage")
    public Result testTxMessage(){
        return messageService.testTxMessage();
    }
}

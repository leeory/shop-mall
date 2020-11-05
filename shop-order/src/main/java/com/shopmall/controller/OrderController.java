package com.shopmall.controller;

import com.shopmall.common.Result;
import com.shopmall.entity.Order;
import com.shopmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("createOrder")
    public Result createOrder(Order order){
        orderService.createOrder(order);
        return Result.success("创建订单成功");
    }


    @GetMapping("queryOrderById")
    public Result queryOrderById(Long orderId){
        Order order = orderService.queryById(orderId);
        return Result.success(order);
    }

}

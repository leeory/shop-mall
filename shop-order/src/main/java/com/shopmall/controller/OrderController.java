package com.shopmall.controller;

import com.shopmall.common.Result;
import com.shopmall.entity.Product;
import com.shopmall.remoteService.ProductRemoteClient;
import com.shopmall.remoteService.ProductRemoteService;
import com.shopmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRemoteClient productRemoteClient;

    @Autowired
    private ProductRemoteService productRemoteService;

    @PostMapping("createOrder")
    public Result createOrder(Long productId,Long userId,Integer number){
        orderService.createOrder(productId,userId,number);
        return Result.success("创建订单成功");
    }


    @GetMapping("queryOrderById/{orderId}")
    public Result queryOrderById(@PathVariable("orderId") Long orderId){
        return orderService.queryById(orderId);
    }


    @GetMapping("queryProduct/{id}")
    public Product queryProduct(@PathVariable("id") Long id){
        return productRemoteService.findProductById(id);
    }

    @GetMapping("reduceStock")
    public Result reduceStock(Long productId,Integer num){
        return productRemoteService.reduceStock(productId,num);
    }


    @GetMapping("api1")
    public Result api1(){
        return Result.success("api1 success");
    }

    @GetMapping("api1/demo1")
    public Result api11(){
        return Result.success("api1 demo1 success");
    }


    @GetMapping("api2")
    public Result api2(){
        return Result.success("api2 success");
    }
}

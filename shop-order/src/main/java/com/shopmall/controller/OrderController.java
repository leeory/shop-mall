package com.shopmall.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.shopmall.common.Result;
import com.shopmall.entity.Product;
import com.shopmall.exceptionHander.CustomerBlockHandler;
import com.shopmall.exceptionHander.CustomerFallbackHandler;
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

    @GetMapping("createOrder")
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


    /**
     *  当发生BlockException时进入handlerException方法，当发生其他异常时，进入fallback方法
     * @return
     */
    @SentinelResource(value = "order/api1",blockHandler = "handlerException",fallback = "fallback")
    @GetMapping("api1")
    public Result api1(){
        return Result.success("api1 success");
    }

    /**
     * 这种处理限流异常，放在业务类中，不可取
     * 参数列表要和主方法一致，另外多一个BlockException参数
     */
    public Result handlerException(BlockException blockException){
        return Result.builder().code("444").message("api1服务不可用").build();
    }

    /**
     * 参数列表要和主方法一致，另外多一个Throwable参数
     * @param throwable
     * @return
     */
    public Result fallback(Throwable throwable){
        return Result.builder().code("555").message("api1发生异常了").build();
    }



    @SentinelResource(value = "order/api1/demo1",blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException1")
    @GetMapping("api1/demo1")
    public Result api11(){
        return Result.success("api1 demo1 success");
    }


    @SentinelResource(value = "order/api2",fallbackClass = CustomerFallbackHandler.class,
                fallback = "fallback")
    @GetMapping("api2")
    public Result api2(){
        return Result.success("api2 success");
    }
}

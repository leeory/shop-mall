package com.shopmall.service;

import com.shopmall.common.Result;
import com.shopmall.dao.OrderMapper;
import com.shopmall.entity.Order;
import com.shopmall.entity.Product;
import com.shopmall.remoteService.ProductRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class OrderServiceImpl  implements  OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductRemoteService productRemoteService;

    @Transactional
    @Override
    public Result createOrder(Long productId,Long userId,Integer number) {
        Product product = productRemoteService.findProductById(productId);
        if(product == null){
            return Result.builder().code("22").message("未查询到商品").build();
        }
        if(product.getStock() < number){
            return Result.builder().code("22").message("库存不够，无法购买").build();
        }
        //创建订单
        Order order = new Order();
        order.setProductId(productId);
        order.setProductName(product.getProductName());
        order.setNumber(number);
        order.setDeleteFlag(false);
        order.setCreateTime(new Date());
        order.setUserId(userId);
        orderMapper.insertSelective(order);
        //扣减库存
        productRemoteService.reduceStock(productId,number);
        return Result.success(order);
    }

    @Override
    public Result queryById(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        return Result.success(order);
    }
}

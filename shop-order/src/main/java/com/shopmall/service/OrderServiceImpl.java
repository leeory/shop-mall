package com.shopmall.service;

import com.shopmall.dao.OrderMapper;
import com.shopmall.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class OrderServiceImpl  implements  OrderService{

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public int createOrder(Order order) {
        order.setCreateTime(new Date());
        order.setDeleteFlag(false);
        int row = orderMapper.insertSelective(order);
        log.info("创建订单数量：{}",row);
        return row;
    }

    @Override
    public Order queryById(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}

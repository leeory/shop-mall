package com.shopmall.service;

import com.shopmall.dao.OrderMapper;
import com.shopmall.entity.Order;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;


public interface OrderService {


    int createOrder(Order order);

    Order queryById(Long orderId);

}

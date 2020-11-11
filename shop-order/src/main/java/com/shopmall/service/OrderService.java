package com.shopmall.service;

import com.shopmall.common.Result;
import com.shopmall.dao.OrderMapper;
import com.shopmall.entity.Order;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;


public interface OrderService {


    Result createOrder(Long productId,Long userId,Integer number);

    Result queryById(Long orderId);

}

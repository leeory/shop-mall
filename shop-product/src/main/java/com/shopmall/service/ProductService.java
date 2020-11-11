package com.shopmall.service;

import com.shopmall.entity.Product;

public interface ProductService {

    Product queryById(Long id);


    int save(Product product);


    /**
     * 减库存
     * @param productId 商品id
     * @param num 数量
     */
    int reduceStock(Long productId,Integer num);
}

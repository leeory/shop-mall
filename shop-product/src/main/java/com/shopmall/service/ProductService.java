package com.shopmall.service;

import com.shopmall.entity.Product;

public interface ProductService {

    Product queryById(Long id);


    int save(Product product);
}

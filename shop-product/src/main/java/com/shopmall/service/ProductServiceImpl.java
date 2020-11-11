package com.shopmall.service;

import com.shopmall.dao.ProductMapper;
import com.shopmall.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class ProductServiceImpl  implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product queryById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(Product product) {
        product.setCreateTime(new Date());
        product.setDeleteFlag(false);
        return productMapper.insertSelective(product);
    }

    @Override
    public int reduceStock(Long productId, Integer num) {
        Product product = queryById(productId);
        //库存小于购买数量时候，不能下单成功
        if(product.getStock() < num){
            return 0;
        }
        product.setStock(product.getStock()- num);
        return productMapper.updateByPrimaryKeySelective(product);
    }
}

package com.shopmall.controller;

import com.shopmall.common.Result;
import com.shopmall.entity.Product;
import com.shopmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("queryProductById/{productId}")
    public Product queryProductById(@PathVariable("productId") Long productId){
        Product product = productService.queryById(productId);
        return product;
    }


    @PostMapping("addProduct")
    public Result addProduct(Product product){
        int row = productService.save(product);
        return Result.success("保存产品成功");
    }


    @PostMapping("reduceStock")
    public Result reduceStock(Long  productId,Integer num){
        int row = productService.reduceStock(productId,num);
        return row > 0?Result.success("扣减商品库存成功"):Result.success("扣减商品库存失败");
    }
}

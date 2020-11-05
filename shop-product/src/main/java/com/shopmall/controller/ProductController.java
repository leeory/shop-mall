package com.shopmall.controller;

import com.shopmall.common.Result;
import com.shopmall.entity.Product;
import com.shopmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("queryProductById")
    public Result queryProductById(Long productId){
        Product product = productService.queryById(productId);
        return Result.success(product);
    }


    @PostMapping("addProduct")
    public Result addProduct(Product product){
        int row = productService.save(product);
        return Result.success("保存产品成功");
    }
}

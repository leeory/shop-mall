package com.shopmall.remoteService;

import com.shopmall.common.Result;
import com.shopmall.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 使用feign调用微服务，feign集成了ribbon实现负载
 */
@FeignClient(value = "service-product")
public interface ProductRemoteService {

    @GetMapping("product/queryProductById/{id}")
     Product findProductById(@PathVariable("id") Long id);

    @PostMapping("product/reduceStock")
    Result reduceStock(@RequestParam("productId") Long productId,@RequestParam("num") Integer num);

}

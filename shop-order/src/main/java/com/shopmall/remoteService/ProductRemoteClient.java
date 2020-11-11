package com.shopmall.remoteService;

import com.shopmall.common.Result;
import com.shopmall.entity.Order;
import com.shopmall.entity.Product;
import com.shopmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;


/**
 * 调用远程微服务
 */

@Slf4j
@Component
public class ProductRemoteClient {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     * 使用Ribbon负载均衡调用服务
     *
     * ribbon：负载均衡策略 在配置文件中加入如下配置
     * service-product:   #调用者的服务名
     *   ribbon:
     *     NFLoadBalanceRuleClassName: com.netflix.loadBalancer.RandomRule
     *
     */
    public Product queryProductByRibbon(Long productId){
        //微服务可能集群部署，所以返回集合。
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance instance = instances.get(0);
        Product result = restTemplate.getForObject("http://service-product/product/queryProductById/"+productId, Product.class);
        return result;
    }


}

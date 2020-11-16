package com.shopmall.controller;

import com.shopmall.common.Result;
import org.apache.http.client.methods.Configurable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("naocs")
@RefreshScope //动态的刷新配置文件的值
public class NacosConfigController {

    @Value("${config.appName}")
    private String appName;

//    @Value("${company.name}")
//    private String name;

    @Autowired
    private ConfigurableApplicationContext context;

    /**
     * 1.此种方式是支持动态刷新的，即在nacos配置中心，修改此值，立马就能看到这个值已经改变了，但不友好。
     * @return
     */
    @GetMapping("testGetValue")
    public Result testGetValue(){
        return Result.success(context.getEnvironment().getProperty("config.appName"));
    }

    /**
     *  2.在类上加上@RefreshScope注解，实现动态的刷新配置，推荐使用这种方式
     * @return
     */
    @GetMapping("testGetConfigValue")
    public Result testGetConfigValue(){
        return Result.success(appName);
    }

    /**
     * 取放在公共配置文件中的值,经测试放在公共配置里面的配置信息， 两种方式都不能实时刷新。
     * @return
     */
    @GetMapping("testGetValue1")
    public Result testGetValue1(){
        return Result.success(context.getEnvironment().getProperty("company.name"));
    }

}

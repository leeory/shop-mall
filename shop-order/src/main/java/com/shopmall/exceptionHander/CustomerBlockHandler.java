package com.shopmall.exceptionHander;

import com.shopmall.common.Result;
import com.sun.deploy.security.BlockedException;

/**
 * sentinel 限流，熔断，降级统一异常处理类，可以都定义在此类中，出现异常时根据需要调用对应的方法
 */
public class CustomerBlockHandler {


    //注意这里的方法必须时static的，否则不生效
    public static Result handlerException(BlockedException exception){
        return Result.builder().code("444").message("服务不可用").build();
    }

    public static Result handlerException1(BlockedException exception){
        return Result.builder().code("444").message("服务不可用1").build();
    }

    public static Result handlerException2(BlockedException exception){
        return Result.builder().code("444").message("服务不可用2").build();
    }
}

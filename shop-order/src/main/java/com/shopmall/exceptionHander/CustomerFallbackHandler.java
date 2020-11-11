package com.shopmall.exceptionHander;

import com.shopmall.common.Result;
import org.springframework.stereotype.Component;

/**
 * 统一的异常限流的异常处理类
 */
public class CustomerFallbackHandler {

    /**
     * 注意此处方法必须时static的否则不生效
     * @param throwable
     * @return
     */
    public static Result fallback(Throwable throwable){
        return Result.builder().code("555").message("服务发生异常了").build();
    }
}

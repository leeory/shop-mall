package com.shopmall.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 *
 * 全局过滤器作用于所有路由，无需配置，通过全局过滤器可以实现对权限的统一校验，安全验证等功能。
 *
 * 自定义全局过滤器：必须实现GlobalFilter和Ordered两个接口，并且实现里面的两个方法
 */

//@Slf4j
//@Component
public class AuthGlobalFilter implements GlobalFilter,Ordered {


    /**
     * 过滤器逻辑：统一鉴权
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(!StringUtils.equals(token,"admin")){
            //log.info("认证失败了。。。");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return  exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }


    /**
     *
     * @return 标识当前过滤器的优先级，返回值越小，优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}

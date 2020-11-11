package com.shopmall.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 *过滤器作用：在请求传递过程中，对请求和响应做一些手脚
 *生命周期：
 *  PreFilter：这种过滤器在请求之前调用，我们可以利用这种过滤器实现身份验证，
 *             在集群中选择请求的微服务，记录调试信息等。
 *  PostFilter：这种过滤器在路由到微服务以后执行，这种过滤器为响应添加标准的HTTP Header，
 *              收集统计信息和指标，将响应从微服务发送给客户端等。
 *
 *spring cloud Gateway的Filter从作用范围可分为两种：
 *  GatewayFilter(局部过滤器) ：作用在单个路由或一个分组的路由上
 *  GlobalFilter（全局过滤器）：作用在所有路由上
 *
 * 自定义的局部过滤器
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    /**
     * 构造函数
     */
    public LogGatewayFilterFactory(){
        super(LogGatewayFilterFactory.Config.class);
    }

    /**
     * 读取配置文件中的参数并赋值给配置类
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }


    /**
     * 过滤器逻辑，这里只是写个例子，没有具体的业务实现
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if(config.consoleLog){
                    System.out.println("consoleLog 已经开启了。。。");
                }
                if(config.cacheLog){
                    System.out.println("cacheLog 已经开启了。。。");
                }
                return chain.filter(exchange); //将过滤器链传递下去
            }
        };
    }


    /**
     * 配置类
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;
    }
}

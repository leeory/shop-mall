package com.shopmall.predicate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义路由断言 （参照 内置断言->BeforeRoutePredicateFactory）
 * 固定的写法:
 *  1,名字必须是 ： 配置+RoutePredicateFactory
 *  2,必须继承AbstractRoutePredicateFactory<配置类>
 * 配置文件添加对应配置：
 *  predicates:
 *    - Age=18,60
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {


    /**
     * 构造函数
     */
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 读取配置文件中的参数，并赋值到配置类中的属性上去,
     * @param
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        //必须注意这里值的顺序要和配置文件中的顺序保持一致
        return Arrays.asList("minAge","maxAge");
    }


    /**
     * 断言处理逻辑 (这里基于WebFlux技术)：在请求路径中加入age参数，要求值范围在[18,60]
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //1，接收前面传入的参数
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                //2，先判断是否非为空
                if(StringUtils.isNotEmpty(ageStr)){
                    //如果不为空
                    int age = Integer.parseInt(ageStr);
                    if(age <= config.getMaxAge() && age >= config.getMinAge()){
                        return true; //放行
                    }else {
                        return false;
                    }
                }
                return false;
            }
        };
    }

    /**
     * 配置类：用于接收配置文件中对应的参数
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge;
        private  int maxAge;
    }
}

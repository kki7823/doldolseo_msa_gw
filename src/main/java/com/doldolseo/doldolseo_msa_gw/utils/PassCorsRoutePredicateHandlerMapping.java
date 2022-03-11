package com.doldolseo.doldolseo_msa_gw.utils;

import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.core.env.Environment;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*
    Default로 설정되어 있는 CORS Prefilght 요청에대한 응답 로직 비활성
    해당 로직이 활성 되어 있으면, 브라우저의 CORS Prefilgnr요청을 서비스가 아닌 Gw가 응답처리를 하게 됨
    코드 출처 : https://yangbongsoo.tistory.com/18
*/
public class PassCorsRoutePredicateHandlerMapping extends RoutePredicateHandlerMapping {

    public PassCorsRoutePredicateHandlerMapping(FilteringWebHandler webHandler,
                                                RouteLocator routeLocator,
                                                GlobalCorsProperties globalCorsProperties,
                                                Environment environment) {
        super(webHandler, routeLocator, globalCorsProperties, environment);
    }

    @Override
    public Mono<Object> getHandler(ServerWebExchange exchange) {
        System.out.println("[PassCorsRoutePredicateHandlerMapping] getHandler");

        return getHandlerInternal(exchange).map(handler -> {
            System.out.println(exchange.getLogPrefix() + "Mapped to " + handler);
            return handler;
        });
    }
}

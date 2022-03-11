package com.doldolseo.doldolseo_msa_gw.utils;

import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.core.env.Environment;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*
    Spring cloud gateway에 Default로 설정되어 있는 CORS Prefilght 요청에 대한 응답 로직 비활성
    해당 로직이 활성 되어 있으면, 브라우저의 CORS Prefilght 요청을  각 마이크로 서비스가 아닌 게이트웨이가 응답처리를 하게 됨
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
        return getHandlerInternal(exchange).map(handler -> {
            return handler;
        });
    }

}

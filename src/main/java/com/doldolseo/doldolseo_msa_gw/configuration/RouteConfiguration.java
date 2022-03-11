package com.doldolseo.doldolseo_msa_gw.configuration;

import com.doldolseo.doldolseo_msa_gw.filter.AuthenticationFilter;
import com.doldolseo.doldolseo_msa_gw.filter.TestFilter;
import com.doldolseo.doldolseo_msa_gw.utils.PassCorsRoutePredicateHandlerMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class RouteConfiguration {
    @Autowired
    AuthenticationFilter authFilter;
    @Autowired
    TestFilter testFilter;

    private final String URI_AREA_REST = "http://doldolseo-area-rest.rest.svc.cluster.local:8080";
        private final String URI_MEMBER_REST = "http://doldolseo-member-rest.rest.svc.cluster.local:8080";
    private final String URI_REVIEW_REST = "http://doldolseo-review-rest.rest.svc.cluster.local:8080";
    private final String URI_CREW_POST_REST = "http://doldolseo-crew-post-rest.rest.svc.cluster.local:8080";
    private final String URI_CREW_REST = "http://doldolseo-crew-rest.rest.svc.cluster.local:8080";

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("doldolseo-area", r -> r.path("/doldolseo/area/**")
                        .filters(f -> f.filter(authFilter))
                        .uri(URI_AREA_REST))
                .route("doldolseo-member", r -> r.path("/doldolseo/member/**")
                        .filters(f -> f.filter(testFilter))
                        .uri(URI_MEMBER_REST))
                .route("doldolseo-review", r -> r.path("/doldolseo/review/**")
                        .filters(f -> f.filter(authFilter))
                        .uri(URI_REVIEW_REST))
                .route("doldolseo-crew-post", r -> r.path("/doldolseo/crew/post/**")
                        .filters(f -> f.filter(authFilter))
                        .uri(URI_CREW_POST_REST))
                .route("doldolseo-crew", r -> r.path("/doldolseo/crew/**")
                        .filters(f -> f.filter(authFilter))
                        .uri(URI_CREW_REST))
                .build();
    }

    @Bean
    @Primary
    public RoutePredicateHandlerMapping passCorsRoutePredicateHandlerMapping(FilteringWebHandler webHandler,
                                                                             RouteLocator routeLocator,
                                                                             GlobalCorsProperties globalCorsProperties,
                                                                             Environment environment) {
        return new PassCorsRoutePredicateHandlerMapping(webHandler, routeLocator, globalCorsProperties, environment);
    }
}

package com.doldolseo.doldolseo_msa_gw.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class RouterValidator {
    public static final List<EndPoint> openApiEndpoints = Arrays.asList(
            new EndPoint(Pattern.compile("(.*)/doldolseo/area(.*)"), "GET"),
            new EndPoint(Pattern.compile("(.*)/doldolseo/member$"), "POST"),
            new EndPoint(Pattern.compile("(.*)/doldolseo/member/login(.*)$"), "POST"),
            new EndPoint(Pattern.compile("(.*)/doldolseo/review(.*)"), "GET"),
            new EndPoint(Pattern.compile("(.*)/doldolseo/review/images/(.*)"), "POST"),
            new EndPoint(Pattern.compile("(.*)/doldolseo/review/images/(.*)"), "GET"),
            new EndPoint(Pattern.compile("(.*)/doldolseo/review/(.[0-9]*)/comment"), "GET")
    );

    public Predicate<ServerHttpRequest> isAuthRequire = request ->
            openApiEndpoints
                    .stream()
                    .noneMatch(endPoint ->
                            endPoint.uriPattern.matcher(request.getURI().getPath()).find()
                                    && endPoint.method.equals(request.getMethodValue()));

    @Data
    @AllArgsConstructor
    private static class EndPoint {
        private Pattern uriPattern;
        private String method;
    }
}

package com.ecommerce.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter; // Fixed interface
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest; // Fixed import
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtGateWayFilter implements GlobalFilter, Ordered { // Implements GlobalFilter

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> PUBLIC_URLS = List.of(
            "/api/users/register",
            "/api/users/login"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if(PUBLIC_URLS.stream().anyMatch(path::contains)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        String token = authHeader.substring(7);
        if(!jwtUtil.validateToken(token)) {
            return unauthorized(exchange);
        }

        String userId = jwtUtil.getUserId(token);
        String role = jwtUtil.getRole(token);

        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                .header("X-User-Id", userId)
                .header("X-User-Role", role)
                .build();

        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }

    // Fixed: Changed Mono<void> to Mono<Void>
    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1; // Fixed: Added return statement to give this filter high priority
    }
}

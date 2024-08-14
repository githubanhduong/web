package com.likelion.web.custom;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class CaptureRedirectUrlFilter implements WebFilter {
    @SuppressWarnings("null")
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        
        // Don't store login page URL
        if (!path.startsWith("/login")) {
            exchange.getSession().map(session -> {
                session.getAttributes().put("ORIGINAL_URL", path);
                return session;
            }).subscribe();
        }

        return chain.filter(exchange);
    }

    private boolean needsAuthentication(ServerWebExchange exchange) {
        // Implement logic to determine if authentication is needed
        return true;
    }
}

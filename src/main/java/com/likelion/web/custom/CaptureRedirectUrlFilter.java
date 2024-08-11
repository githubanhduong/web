package com.likelion.web.custom;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class CaptureRedirectUrlFilter implements WebFilter {
    @SuppressWarnings("null")
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Check if the request needs authentication
        if (needsAuthentication(exchange)) {
            // Store the original URL before redirecting to login
            exchange.getAttributes().put("redirectUrl", exchange.getRequest().getURI().toString());
        }

        return chain.filter(exchange);
    }

    private boolean needsAuthentication(ServerWebExchange exchange) {
        // Implement logic to determine if authentication is needed
        return true;
    }
}

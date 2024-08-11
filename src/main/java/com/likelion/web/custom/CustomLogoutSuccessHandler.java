package com.likelion.web.custom;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.web.server.WebSession;

import reactor.core.publisher.Mono;

public class CustomLogoutSuccessHandler implements ServerLogoutSuccessHandler {
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        exchange.getExchange().getResponse().getCookies().remove("JSESSIONID");
        return exchange.getExchange().getSession()
                .flatMap(WebSession::invalidate);
    }
}


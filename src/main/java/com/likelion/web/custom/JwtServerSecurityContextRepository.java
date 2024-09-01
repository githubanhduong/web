package com.likelion.web.custom;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.likelion.web.filter.JwtAuthenticationManager;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtServerSecurityContextRepository implements ServerSecurityContextRepository {

    private final JwtAuthenticationManager authenticationManager;

    public JwtServerSecurityContextRepository(@Lazy JwtAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty(); // Since JWT is stateless, we don't need to save the context
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring(7);
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
            return this.authenticationManager.authenticate(auth)
            .doOnSuccess(authentication -> log.info("Successfully authenticated user with auth {}", auth))
            .doOnError(ex -> log.error("Authentication failed", ex))
                .map(SecurityContextImpl::new);
        }
        
        return Mono.empty();
    }
}

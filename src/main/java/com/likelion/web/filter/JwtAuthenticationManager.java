package com.likelion.web.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.likelion.web.service.JwtService;
import com.likelion.web.util.JwtToken;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Primary
@Slf4j
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ReactiveUserDetailsService reactiveUserDetailsService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String username = jwtService.extractUsername(token);

        log.info("token: {}", token);

        return reactiveUserDetailsService.findByUsername(username)
                .map(userDetails -> {
                    if (jwtService.validateToken(token, userDetails)) {
                        log.info("authentication:: {}", authentication);
                        // Create a new fully authenticated token
                        return new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                    } else {
                        throw new AuthenticationException("Invalid JWT token") {
                        };
                    }
                })    .cast(Authentication.class)

                .doOnError(exp -> log.error("Exception: ", exp));
    }

    public ServerAuthenticationConverter authenticationConverter() {
        return new ServerAuthenticationConverter() {
            @Override
            public Mono<Authentication> convert(ServerWebExchange exchange) {
                String token = exchange.getRequest().getHeaders().getFirst("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                    return Mono.just(SecurityContextHolder.getContext().getAuthentication());
                }
                return Mono.empty();
            }
        };
    }

    // @Override
    // protected void doFilterInternal(ServerHttpRequest request, ServerHttpResponse
    // response, FilterChain filterChain)
    // throws ServletException, IOException {
    // // Retrieve the Authorization header
    // String authHeader = request.getHeaders().getFirst("Authorization");
    // String token = null;
    // String username = null;

    // // Check if the header starts with "Bearer "
    // if (authHeader != null && authHeader.startsWith("Bearer ")) {
    // token = authHeader.substring(7); // Extract token
    // username = jwtService.extractUsername(token); // Extract username from token
    // }

    // // If the token is valid and no authentication is set in the context
    // if (username != null &&
    // SecurityContextHolder.getContext().getAuthentication() == null) {
    // UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    // // Validate token and set authentication
    // if (jwtService.validateToken(token, userDetails)) {
    // UsernamePasswordAuthenticationToken authToken = new
    // UsernamePasswordAuthenticationToken(
    // userDetails,
    // null,
    // userDetails.getAuthorities());
    // authToken.setDetails(new
    // WebAuthenticationDetailsSource().buildDetails(request));
    // SecurityContextHolder.getContext().setAuthentication(authToken);
    // }
    // }

    // // Continue the filter chain
    // filterChain.doFilter(request, response);
    // }

    // @SuppressWarnings("null")
    // @Override
    // public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    // ServerHttpRequest request = exchange.getRequest();
    // String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    // if (authHeader != null && authHeader.startsWith("Bearer ")) {
    // String token = authHeader.substring(7);
    // String username = jwtService.extractUsername(token);

    // if (username != null) {
    // return this.userDetailsService.findByUsername(username)
    // .filter(userDetails -> jwtService.validateToken(token, userDetails))
    // .map(userDetails -> new UsernamePasswordAuthenticationToken(
    // userDetails,
    // null,
    // userDetails.getAuthorities()
    // ))
    // .flatMap(authentication -> {
    // return chain.filter(exchange)
    // .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
    // })
    // .switchIfEmpty(chain.filter(exchange));
    // }
    // }

    // return chain.filter(exchange);
    // }

    // private Mono<Void> authenticate(ServerWebExchange exchange, String token) {
    // String username = jwtService.extractUsername(token);
    // return Mono.justOrEmpty(username)
    // .flatMap(userDetailsService::findByUsername)
    // .filter(userDetails -> jwtService.validateToken(token, userDetails))
    // .map(userDetails -> new UsernamePasswordAuthenticationToken(
    // userDetails, null, userDetails.getAuthorities()))
    // .flatMap(authentication -> {
    // exchange.getAttributes().put(AUTHORIZATION_ATTRIBUTE, authentication);
    // return exchange.getSession()
    // .doOnNext(session -> session.getAttributes().put(
    // SPRING_SECURITY_CONTEXT_ATTRIBUTES_NAME,
    // new SecurityContextImpl(authentication)));
    // })
    // .then();
    // }

}

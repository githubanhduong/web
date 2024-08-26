package com.likelion.web.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.likelion.web.implement.UserDetailServiceImpl;
import com.likelion.web.service.JWTService;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements WebFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ReactiveUserDetailsService userDetailsService;

    // @Override
    // protected void doFilterInternal(ServerHttpRequest request, ServerHttpResponse response, FilterChain filterChain)
    //         throws ServletException, IOException {
    //     // Retrieve the Authorization header
    //     String authHeader = request.getHeaders().getFirst("Authorization");
    //     String token = null;
    //     String username = null;

    //     // Check if the header starts with "Bearer "
    //     if (authHeader != null && authHeader.startsWith("Bearer ")) {
    //         token = authHeader.substring(7); // Extract token
    //         username = jwtService.extractUsername(token); // Extract username from token
    //     }

    //     // If the token is valid and no authentication is set in the context
    //     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //         UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    //         // Validate token and set authentication
    //         if (jwtService.validateToken(token, userDetails)) {
    //             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
    //                     userDetails,
    //                     null,
    //                     userDetails.getAuthorities());
    //             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //             SecurityContextHolder.getContext().setAuthentication(authToken);
    //         }
    //     }

    //     // Continue the filter chain
    //     filterChain.doFilter(request, response);
    // }

    @SuppressWarnings("null")
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
            
            if (username != null) {
                return this.userDetailsService.findByUsername(username)
                    .filter(userDetails -> jwtService.validateToken(token, userDetails))
                    .map(userDetails -> new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    ))
                    .flatMap(authentication -> {
                        return chain.filter(exchange)
                            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
                    })
                    .switchIfEmpty(chain.filter(exchange));
            }
        }
        
        return chain.filter(exchange);
    }









    // private Mono<Void> authenticate(ServerWebExchange exchange, String token) {
    //     String username = jwtService.extractUsername(token);
    //     return Mono.justOrEmpty(username)
    //             .flatMap(userDetailsService::findByUsername)
    //             .filter(userDetails -> jwtService.validateToken(token, userDetails))
    //             .map(userDetails -> new UsernamePasswordAuthenticationToken(
    //                     userDetails, null, userDetails.getAuthorities()))
    //             .flatMap(authentication -> {
    //                 exchange.getAttributes().put(AUTHORIZATION_ATTRIBUTE, authentication);
    //                 return exchange.getSession()
    //                         .doOnNext(session -> session.getAttributes().put(
    //                                 SPRING_SECURITY_CONTEXT_ATTRIBUTES_NAME,
    //                                 new SecurityContextImpl(authentication)));
    //             })
    //             .then();
    // }

}

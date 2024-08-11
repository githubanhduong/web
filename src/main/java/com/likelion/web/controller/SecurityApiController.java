package com.likelion.web.controller;

import java.net.URI;
import java.util.function.Function;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import com.likelion.web.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SecurityApiController {
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    ServerSecurityContextRepository serverSecurityContextRepository = new WebSessionServerSecurityContextRepository();

    @GetMapping("/signout")
    public Mono<Void> performLogout(WebSession session) {
        return session.invalidate();
    }

    @GetMapping("/login/success")
    public Mono<String> loginSuccess(@AuthenticationPrincipal UserDetails user) {
        return Mono.just("Welcome, " + user.getUsername() + "! Login successful.");
    }

    @GetMapping("/login/failure")
    public Mono<String> loginFailure() {
        return Mono.just("Login failed. Please try again.");
    }

    @GetMapping("/")
    public Mono<String> home(@AuthenticationPrincipal UserDetails user) {
        if (user != null) {
            return Mono.just("Hello, " + user.getUsername() + "! You are logged in.");
        } else {
            return Mono.just("Hello! Please log in.");
        }
    }

    @PostMapping("/login/{redirectUrl}")
    public Mono<ResponseEntity<?>> login(@RequestBody User authRequest,
            @PathVariable String redirectUrl,
            ServerWebExchange exchange) {
        return this.reactiveAuthenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))
                .flatMap((Function<Authentication, Mono<ResponseEntity<?>>>) auth -> {
                    SecurityContext context = new SecurityContextImpl(auth);
                    return this.serverSecurityContextRepository.save(exchange, context)
                            .flatMap(
                                    v -> {
                                        if (redirectUrl != null && !redirectUrl.isEmpty()) {
                                            HttpHeaders headers = new HttpHeaders();
                                            headers.setLocation(URI.create(redirectUrl));
                                            return Mono.just(
                                                    ResponseEntity.status(HttpStatus.FOUND).headers(headers).build());
                                        } else {
                                            return Mono.just(ResponseEntity.ok("Login successful"));
                                        }
                                    });
                })
                .doOnNext(v -> log.info("line 89"))
                .onErrorResume(
                        e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed")));
    }
    
    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login2(@RequestBody User authRequest,
            ServerWebExchange exchange) {
        return this.reactiveAuthenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))
                .flatMap((Function<Authentication, Mono<ResponseEntity<?>>>) auth -> {
                    SecurityContext context = new SecurityContextImpl(auth);
                    return this.serverSecurityContextRepository.save(exchange, context)
                            .flatMap(
                                    v -> {
                                            HttpHeaders headers = new HttpHeaders();
                                            return Mono.just(
                                                    ResponseEntity.status(HttpStatus.FOUND).headers(headers).build());
                                        
                                    });
                })
                .doOnNext(v -> log.info("line 89"))
                .onErrorResume(
                        e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed")));
    }

}

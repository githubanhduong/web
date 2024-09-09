package com.likelion.web.controller;

import java.net.URI;
import java.util.function.Function;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import com.likelion.web.model.User;
// import com.likelion.web.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SecurityApiController {
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    // private final AuthService authService;

    ServerSecurityContextRepository serverSecurityContextRepository = new WebSessionServerSecurityContextRepository();

    @GetMapping("/signout")
    public Mono<Void> performLogout(WebSession session) {
        return session.invalidate();
    }

    // @GetMapping("/login/success")
    // public Mono<String> loginSuccess(@AuthenticationPrincipal UserDetails user) {
    // return Mono.just("Welcome, " + user.getUsername() + "! Login successful.");
    // }

    @GetMapping("/login/failure")
    @ResponseBody
    public Mono<String> loginFailure() {
        return Mono.just("Login failed. Please try again.");
    }

    @PostMapping("/login/failure")
    @ResponseBody
    public Mono<String> loginFailure2() {
        return Mono.just("Login failed. Please try again.");
    }

    @GetMapping("/")
    public Mono<String> home(@AuthenticationPrincipal UserDetails user) {
        return Mono.just("login.html");
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody User authRequest,
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
                                        }
                                    );
                }).doOnError(e -> log.error(e.getMessage()))
                .onErrorResume(
                        e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed")));
    }

    // @PostMapping("/login")
    // public Mono<Void> login2(@RequestBody User authRequest,
    //         ServerWebExchange exchange) {
    //     return this.reactiveAuthenticationManager
    //             .authenticate(
    //                     new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))
    //             .flatMap(auth -> {
    //                 SecurityContext context = new SecurityContextImpl(auth);
    //                 this.serverSecurityContextRepository.save(exchange, context);
    //                 exchange.getResponse().setStatusCode(HttpStatus.FOUND);
    //                 exchange.getResponse().getHeaders().setLocation(URI.create("/login/success"));
    //                 return exchange.getResponse().setComplete();
    //             });

    //     // .onErrorResume(
    //     // e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login
    //     // failed")));
    // }

    // @PostMapping("/login3")
    // public Mono<Void> login3(@RequestBody User authRequest,
    //         ServerWebExchange exchange) {
    //     return this.reactiveAuthenticationManager
    //             .authenticate(
    //                     new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))
    //             .flatMap(auth -> {
    //                 SecurityContext context = new SecurityContextImpl(auth);
    //                 return this.serverSecurityContextRepository.save(exchange, context)
    //                         .then(exchange.getSession())
    //                         .flatMap(session -> {
    //                             ServerHttpResponse response = exchange.getResponse();
    //                             response.setStatusCode(HttpStatus.FOUND);

    //                             String originalUrl = (String) session.getAttributes().get("ORIGINAL_URL");
    //                             if (originalUrl == null || originalUrl.isEmpty()) {
    //                                 originalUrl = "/home";
    //                             }

    //                             response.getHeaders().setLocation(URI.create(originalUrl));

    //                             // Remove the stored URL after using it
    //                             session.getAttributes().remove("ORIGINAL_URL");

    //                             return response.setComplete();
    //                         });
    //             })
    //             .onErrorResume(e -> {
    //                 ServerHttpResponse response = exchange.getResponse();
    //                 response.setStatusCode(HttpStatus.UNAUTHORIZED);
    //                 return response.writeWith(Mono.just(response.bufferFactory()
    //                         .wrap("Login failed".getBytes())));
    //             });
    // }

}

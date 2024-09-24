package com.likelion.web.config;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.likelion.web.filter.RateLimiterHandlerFilterFunction;

@Configuration
public class HtmlRoutesConfig {
    @Autowired
    private ReactiveRedisTemplate<String, Long> redisTemplate;

    // @Bean
    // RouterFunction<ServerResponse> htmlRouter(@Value("classpath:/static/**")
    // Resource html) {
    // return RouterFunctions.route()
    // .GET("/static/*.html", request -> ServerResponse.ok()
    // .contentType(MediaType.TEXT_HTML)
    // .bodyValue(html))
    // .build();
    // }

    @Bean
    RouterFunction<ServerResponse> imgRouter() {
        return RouterFunctions
                .resources("/**", new ClassPathResource("/"));
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route() //
                .GET("/api/ping", r -> ok() //
                        .contentType(TEXT_PLAIN) //
                        .body(BodyInserters.fromValue("PONG")) //
                ).filter(new RateLimiterHandlerFilterFunction(redisTemplate)).build();
    }

}

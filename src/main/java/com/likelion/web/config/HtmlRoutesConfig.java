package com.likelion.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HtmlRoutesConfig {

    // @Bean
    // RouterFunction<ServerResponse> htmlRouter(@Value("classpath:/static/**") Resource html) {
    //     return RouterFunctions.route()
    //             .GET("/static/*.html", request -> ServerResponse.ok()
    //                     .contentType(MediaType.TEXT_HTML)
    //                     .bodyValue(html))
    //             .build();
    // }

    @Bean
    RouterFunction<ServerResponse> imgRouter() {
        return RouterFunctions
                .resources("/**", new ClassPathResource("static/"));
    }
}


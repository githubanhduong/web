// package com.likelion.web.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.reactive.config.WebFluxConfigurer;
// import org.springframework.web.reactive.result.view.ViewResolver;
// import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;
// import org.springframework.web.reactive.result.view.freemarker.FreeMarkerViewResolver;

// @Configuration
// public class WebConfig implements WebFluxConfigurer {
//     @Bean
//     FreeMarkerConfigurer freeMarkerConfigurer() {
//         FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//         configurer.setTemplateLoaderPath("classpath:/templates");
//         return configurer;
//     }

//     @Bean
//     ViewResolver viewResolver() {
//         FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//         resolver.setPrefix("");
//         resolver.setSuffix(".ftl");
//         return resolver;
//     }
// }
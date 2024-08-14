package com.likelion.web.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.net.URI;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;

import com.likelion.web.custom.CaptureRedirectUrlFilter;
import com.likelion.web.custom.CustomAuthenticationSuccessHandler;
import com.likelion.web.implement.UserDetailServiceImpl;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    UserDetailServiceImpl userDetailsServiceImpl;

    @Autowired
    DataSource dataSource;

    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(
                userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }

    @Bean
    ReactiveUserDetailsService userDetailsService() {
        return (username) -> userDetailsServiceImpl.loadUserByUsername(username, "password");
    }

    /**
     * @param http
     * @return
     */
    @SuppressWarnings("removal")
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/static/**").permitAll()
                        .pathMatchers("/login/**").permitAll()
                        .pathMatchers("/signout").permitAll()
                        .pathMatchers("/signup").permitAll()
                        .pathMatchers("/resetpassword").permitAll()
                        .pathMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                        .anyExchange().authenticated())
                // .httpBasic(withDefaults())
                .formLogin(form -> form
                        .loginPage("static/login.html")
                        // .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                        //     ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
                        //     response.setStatusCode(HttpStatus.FOUND);
                        //     response.getHeaders().setLocation(URI.create("/login/success"));
                        //     return Mono.empty();
                        // })
                        .authenticationSuccessHandler(authenticationSuccessHandler())
                        .authenticationFailureHandler((webFilterExchange, exception) -> {
                            ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
                            response.setStatusCode(HttpStatus.FOUND);
                            response.getHeaders().setLocation(URI.create("/login/failure"));
                            return Mono.empty();
                        }))
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessHandler((webFilterExchange, authentication) -> Mono.fromRunnable(
                                () -> webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.OK))))
//              .logout()
//                  .requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, "/logout"))
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable());

        // .logout((logout) -> logout
        // // configures how log out is done
        // // .logoutHandler(logoutHandler)
        // // log out will be performed on POST /signout
        // .logoutUrl("/signout")
        // // configure what is done on logout success
        // .logoutSuccessHandler(new CustomLogoutSuccessHandler()));
        return http.build();
    }

    /**
     * @return
     */
    // @Bean
    // MapReactiveUserDetailsService mapReactiveUserDetailsService() {
    // @SuppressWarnings("deprecation")
    // UserDetails user = User.withDefaultPasswordEncoder()
    // .username("user")
    // .password("user")
    // .roles("USER", "ADMIN")
    // .build();
    // return new MapReactiveUserDetailsService(user);
    // }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    WebFilter captureRedirectUrlFilter() {
        return new CaptureRedirectUrlFilter();
    }

    @Bean
    ServerAuthenticationSuccessHandler authenticationSuccessHandler() {
        return (webFilterExchange, authentication) -> {
            ServerWebExchange exchange = webFilterExchange.getExchange();
            return Mono.fromRunnable(() -> {
                exchange.getResponse().setStatusCode(HttpStatus.FOUND);
                exchange.getResponse().getHeaders().setLocation(URI.create("/home"));
            });
        };
    }

}

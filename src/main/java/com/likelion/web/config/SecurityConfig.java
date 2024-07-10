package com.likelion.web.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.likelion.web.implement.UserDetailServiceImpl;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    UserDetailServiceImpl userDetailsServiceImpl;

    @Autowired
    DataSource dataSource;

    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(
            userDetailsService
        );
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
                        .pathMatchers("/*.html/**").permitAll()
                        .pathMatchers("/*.svg/**").permitAll()
                        .pathMatchers("/assets/**").permitAll()
                        .pathMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                        .anyExchange().authenticated())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable());
        return http.build();
    }
    
    /**
     * @return
     */
    // @Bean
    // MapReactiveUserDetailsService mapReactiveUserDetailsService() {
    //     @SuppressWarnings("deprecation")
    //     UserDetails user = User.withDefaultPasswordEncoder()
    //             .username("user")
    //             .password("user")
    //             .roles("USER", "ADMIN")
    //             .build();
    //     return new MapReactiveUserDetailsService(user);
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
}

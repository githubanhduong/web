// package com.likelion.web.service;

// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.likelion.web.repository.UserRepository;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import reactor.core.publisher.Mono;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class AuthService {
//     private final ReactiveUserDetailsService reactiveUserDetailsService;
//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
    
//     public Mono<AuthResponse> login(LoginRequest request) {
//         log.debug("Starting login process for user: {}", request.getUsername());
//         if (request.getUsername() == null || request.getPassword() == null) {
//             log.error("Username or password cannot be null");
//             return Mono.error(new IllegalArgumentException("Username or password cannot be null"));
//         }

//         return reactiveUserDetailsService.findByUsername(request.getUsername())
//                 .flatMap(userDetails -> {
//                     if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
//                         log.debug("Authenticated user: {}", userDetails.getUsername());
//                         return jwtService.getToken(userDetails)
//                                 .map(token -> {
//                                     log.debug("Generated token for user: {}", token);
//                                     return AuthResponse.builder().token(token).build();
//                                 });
//                     } else {
//                         return Mono.error(new BadCredentialsException("Invalid username or password"));
//                     }
//                 })
//                 .onErrorResume(e -> {
//                     log.error("Error during authentication: {}", e.getMessage());
//                     if (e instanceof BadCredentialsException) {
//                         return Mono.just(AuthResponse.builder().error("Invalid username or password").build());
//                     } else if (e instanceof JwtException) {
//                         return Mono.just(AuthResponse.builder().error("JWT error: " + e.getMessage()).build());
//                     } else {
//                         return Mono.just(AuthResponse.builder().error("Authentication error: " + e.getMessage()).build());
//                     }
//                 });
//     }

// }

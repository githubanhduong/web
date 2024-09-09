package com.likelion.web.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.likelion.web.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public Mono<UserDetails> loadUserByUsername(String username, String password) {
        Mono<UserDetails> user = Mono.just(userRepository.findByUsername(username));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return Mono.just(new UserDetailImpl(user));
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        Mono<UserDetails> user = Mono.just(userRepository.findByUsername(username));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailImpl(user);
    }

}

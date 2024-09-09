package com.likelion.web.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.likelion.web.dto.UserDto;
import com.likelion.web.model.NewLocationToken;
import com.likelion.web.model.PasswordResetToken;
import com.likelion.web.model.User;
import com.likelion.web.model.VerificationToken;
import com.likelion.web.repository.PasswordResetTokenRepository;
import com.likelion.web.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        user.setPassword(user.getPassword()); // Encrypt password before saving
        return userRepository.save(user);
    }


}

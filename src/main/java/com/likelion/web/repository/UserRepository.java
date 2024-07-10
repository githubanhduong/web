package com.likelion.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.likelion.web.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDetails findByUsername(String username);

}

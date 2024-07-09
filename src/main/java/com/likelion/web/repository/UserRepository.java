package com.likelion.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.web.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}

package com.likelion.web.service;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
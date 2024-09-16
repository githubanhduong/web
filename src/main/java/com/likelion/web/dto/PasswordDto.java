package com.likelion.web.dto;

import com.likelion.web.validation.ValidPassword;

public class PasswordDto {

    @ValidPassword
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
package com.likelion.web.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.web.dto.UserDto;
import com.likelion.web.model.NewLocationToken;
import com.likelion.web.model.PasswordResetToken;
import com.likelion.web.model.User;
import com.likelion.web.model.VerificationToken;
import com.likelion.web.repository.PasswordResetTokenRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Override
    public User findUserByEmail(String userEmail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserByEmail'");
    }

    @Override
    public User registerNewUserAccount(UserDto accountDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerNewUserAccount'");
    }

    @Override
    public User getUser(String verificationToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public void saveRegisteredUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveRegisteredUser'");
    }

    @Override
    public void deleteUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createVerificationTokenForUser'");
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVerificationToken'");
    }

    @Override
    public VerificationToken generateNewVerificationToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateNewVerificationToken'");
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPasswordResetToken'");
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByPasswordResetToken'");
    }

    @Override
    public Optional<User> getUserByID(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByID'");
    }

    @Override
    public void changeUserPassword(User user, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeUserPassword'");
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkIfValidOldPassword'");
    }

    @Override
    public String validateVerificationToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateVerificationToken'");
    }

    @Override
    public String generateQRUrl(User user) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateQRUrl'");
    }

    @Override
    public User updateUser2FA(boolean use2fa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser2FA'");
    }

    @Override
    public List<String> getUsersFromSessionRegistry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsersFromSessionRegistry'");
    }

    @Override
    public NewLocationToken isNewLoginLocation(String username, String ip) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNewLoginLocation'");
    }

    @Override
    public String isValidNewLocationToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValidNewLocationToken'");
    }

    @Override
    public void addUserLocation(User user, String ip) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUserLocation'");
    }

}

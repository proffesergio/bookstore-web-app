package me.hossainbillal.bookstore.service.impl;

import me.hossainbillal.bookstore.domain.model.User;
import me.hossainbillal.bookstore.domain.security.ResetPasswordToken;
import me.hossainbillal.bookstore.repository.ResetPasswordTokenRepository;
import me.hossainbillal.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Override
    public ResetPasswordToken getPasswordToken(final String token) {
        return resetPasswordTokenRepository.findByToken(token);
    }

    @Override
    public void createResetPasswordToken(final User user, final String token) {
        final ResetPasswordToken passwordToken = new ResetPasswordToken(token, user);
        resetPasswordTokenRepository.save(passwordToken);
    }
}

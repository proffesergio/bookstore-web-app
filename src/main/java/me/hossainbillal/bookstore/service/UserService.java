package me.hossainbillal.bookstore.service;

import me.hossainbillal.bookstore.domain.model.User;
import me.hossainbillal.bookstore.domain.security.ResetPasswordToken;

public interface UserService {
    ResetPasswordToken getPasswordToken(final String token);

    void createResetPasswordToken(final User user, final String token);
}

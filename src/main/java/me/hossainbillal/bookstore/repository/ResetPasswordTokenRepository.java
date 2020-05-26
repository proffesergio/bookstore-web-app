package me.hossainbillal.bookstore.repository;

import me.hossainbillal.bookstore.domain.model.User;
import me.hossainbillal.bookstore.domain.security.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);

    ResetPasswordToken findByUser(User user);

    Stream<ResetPasswordToken> findAllByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from ResetPasswordToken  t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}

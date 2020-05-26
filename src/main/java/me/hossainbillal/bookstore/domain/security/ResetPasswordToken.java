package me.hossainbillal.bookstore.domain.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.hossainbillal.bookstore.domain.model.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class ResetPasswordToken {
    private static final int EXPIRATION_TIME = 60 * 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

    @OneToOne
    @JoinColumn(nullable = false)
    private User user;

    private Date expiryDate;

    public ResetPasswordToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION_TIME);
    }

    private Date calculateExpiryDate(final int expirationTime) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION_TIME);
    }
}

package com.zimttech.diabeticscreening.token;

import com.zimttech.diabeticscreening.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(
        name = "Token"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "token"
)
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    private String accessToken;

    private String refreshToken;

    @Enumerated(
            EnumType.STRING
    )
    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;



    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
}

package com.zimttech.diabeticscreening.user;

import com.zimttech.diabeticscreening.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(
        name = "User"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "_user"
)
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    @Column(
            unique = true
    )
    private String nationalId;

    private String password;
    @Enumerated(
            EnumType.STRING
    )
    private Role role;

    @OneToMany(
            mappedBy = "user"
    )
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nationalId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


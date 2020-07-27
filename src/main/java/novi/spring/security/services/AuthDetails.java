package novi.spring.security.services;

import java.util.Collection;

import lombok.Getter;
import novi.spring.helper.RoleConverter;
import novi.spring.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthDetails implements UserDetails {

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    private String email;

    @Getter
    @JsonIgnore
    private String password;

    @Getter
    private Collection<GrantedAuthority> authorities;

    public AuthDetails(User user) {
        this.id = user.getId();
        this.username = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = RoleConverter.rolesToAuthorities(user.getRoles());
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
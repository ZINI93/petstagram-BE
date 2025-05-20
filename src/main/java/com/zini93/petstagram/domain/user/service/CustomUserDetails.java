package com.zini93.petstagram.domain.user.service;

import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.exception.UserNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        if (user == null) {
            throw new UserNotFoundException("User can't be null");
        }
        this.user = user;
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public String getUserUuid() {
        return user.getUserUuid();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
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

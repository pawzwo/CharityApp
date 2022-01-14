package com.charity.charityapp.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private final com.charity.charityapp.user.User user;

    public CurrentUser(String email, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.charity.charityapp.user.User user) {
        super(email, password, authorities);
        this.user = user;
    }

    public com.charity.charityapp.user.User getUser() {
        return user;
    }
}
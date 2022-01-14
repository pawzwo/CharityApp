package com.charity.charityapp.user;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    void createUser(User user);

}
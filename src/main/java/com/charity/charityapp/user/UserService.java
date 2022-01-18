package com.charity.charityapp.user;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    void createUser(User user);

    void updateUserDetails(String firstName, String lastName, String email, String street, String city, String zipCode, String phone, long id);

}
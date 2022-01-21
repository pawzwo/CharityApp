package com.charity.charityapp.user;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    User createUser(User user);

    void createAdmin(User user);

    void updateUserDetails(String firstName, String lastName, String email, String street, String city, String zipCode, String phone, long id);

    List<User> findAll();

    List<User> findAllAdmins();

    List<User> findAllNonAdmin();

    boolean checkEnabled(CurrentUser currentUser);

}
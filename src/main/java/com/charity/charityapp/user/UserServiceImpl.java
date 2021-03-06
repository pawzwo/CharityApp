package com.charity.charityapp.user;

import com.charity.charityapp.role.Role;
import com.charity.charityapp.role.RoleRepository;
import lombok.Data;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByEmail(String login) {
        return userRepository.findByEmail(login);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(List.of(userRole)));
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUserDetails(String firstName, String lastName, String email, String street, String city, String zipCode, String phone, long id) {
        userRepository.updateUserDetails(firstName, lastName, email, street, city, zipCode, phone, id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    //Admin

    @Override
    public void createAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getLastName()+"admin"));
        user.setEnabled(1);
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(List.of(adminRole)));
        userRepository.save(user);
    }

    @Override
    public List<User> findAllAdmins() {
        return userRepository.findAllAdmins();
    }

    @Override
    public List<User> findAllNonAdmin() {
        return userRepository.findAllNonAdmin();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean checkEnabled(CurrentUser currentUser) {
        return findByEmail(currentUser.getUser().getEmail()).getEnabled()==1;
    }

    @Override
    public void enableUser(long id) {
        userRepository.enableUser(id);
    }

    @Override
    public void disableUser(long id) {
        userRepository.disableUser(id);
    }
}
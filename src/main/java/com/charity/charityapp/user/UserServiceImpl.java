package com.charity.charityapp.user;

import com.charity.charityapp.role.RoleRepository;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
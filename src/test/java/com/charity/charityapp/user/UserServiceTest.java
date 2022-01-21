package com.charity.charityapp.user;

import com.charity.charityapp.role.Role;
import com.charity.charityapp.role.RoleRepository;
import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@DataJpaTest
class UserServiceTest {

    @Autowired
    TestEntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    private UserService userService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(roleRepository, userRepository, bCryptPasswordEncoder);
    }

    @Test
    void findByEmail() {

        User user = new User();
        user.setEmail("user@email.pl");
        when(userRepository.findByEmail("user@email.pl")).thenReturn(user);
        User user1 = userRepository.findByEmail("user@email.pl");
        assertEquals(user1.getEmail(), "user@email.pl");

    }

    @Test
    void createUser() {
        User user = new User();
        user.setEmail("email@com");
        user.setLastName("Zwolinski");
        user.setPassword(bCryptPasswordEncoder.encode(user.getLastName()+"admin"));
        user.setEnabled(1);
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(List.of(adminRole)));
        entityManager.persist(user);

        User user1 = new User();
        user1.setEmail("email@com");
        user1.setLastName("Zwolinski");

        when(userService.createUser(user1)).thenReturn(user1);

        assertEquals(user.getEnabled(), userService.createUser(user1).getEnabled());

    }

    @Test
    void createAdmin() {
    }

    @Test
    void updateUserDetails() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllAdmins() {
    }

    @Test
    void findAllNonAdmin() {
    }

    @Test
    void checkEnabled() {
    }
}
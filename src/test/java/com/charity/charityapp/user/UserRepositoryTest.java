package com.charity.charityapp.user;

import com.charity.charityapp.role.Role;
import com.charity.charityapp.role.RoleRepository;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByEmail() {

        User user = new User();
        user.setEmail("user@com.pl");
        entityManager.persist(user);
        User result = userRepository.findByEmail("user@com.pl");
        assertEquals(result.getEmail(), user.getEmail());
    }

    @Test
    void findAllAdmins() {
        List<User> users = new ArrayList<>();
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        User user1 = new User();
        User user2 = new User();

        user1.setEmail("user1@com.pl");
        user1.setRoles(new HashSet<>(List.of(adminRole)));
        entityManager.persist(user1);

        user2.setEmail("user2@com.pl");
        user2.setRoles(new HashSet<>(List.of(userRole)));
        entityManager.persist(user2);

        users.add(user1);
        List<User> result = userRepository.findAllAdmins();
        assertEquals(result.size(), users.size());
    }

    @Test
    void findAllNonAdmin() {
    }
}
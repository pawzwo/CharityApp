package com.charity.charityapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query (value = "UPDATE users SET first_name=?1, last_name=?2, email=?3, street=?4, city=?5, zip_code=?6, phone=?7 WHERE id=?8", nativeQuery = true)
    void updateUserDetails(String firstName, String lastName, String email, String street, String city, String zipCode, String phone, long id);


    //Admin
    @Modifying
    @Transactional
    @Query (value = "UPDATE users SET enabled=1 WHERE id=?1", nativeQuery = true)
    void enableUser(long id);

    @Modifying
    @Transactional
    @Query (value = "UPDATE users SET enabled=0 WHERE id=?1", nativeQuery = true)
    void disableUser(long id);

    @Query(value = "SELECT * FROM users JOIN user_role ur on users.id = ur.user_id WHERE role_id=1", nativeQuery = true)
    List<User> findAllAdmins();

    @Query(value = "SELECT * FROM users JOIN user_role ur on users.id = ur.user_id WHERE role_id=2", nativeQuery = true)
    List<User> findAllNonAdmin();

    @Query(value = "SELECT * FROM users WHERE id=?1", nativeQuery = true)
    User findById(long id);


}
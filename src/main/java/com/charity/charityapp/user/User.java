package com.charity.charityapp.user;

import com.charity.charityapp.role.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Email(message = "Please use a correct email")
    private String email;
    @Size(min = 5, message = "Must have at least five characters")
    private String password;

    @Size(min = 2, message = "Must have at least two characters")
    private String firstName;
    @Size(min = 2, message = "Must have at least two characters")
    private String lastName;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Size(min = 2, message = "Must have at least two characters")
    private String phone;
    @Size(min = 2, message = "Must have at least two characters")
    private String street;
    @Size(min = 2, message = "Must have at least two characters")
    private String zipCode;
    @Size(min = 2, message = "Must have at least two characters")
    private String city;


}
package com.charity.charityapp.institution;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2)
    @Column(unique = true)
    private String name;

    @Size(min = 10)
    @Column(unique = true)
    private String description;

    private int enabled;

    private String email;
    private String phone;
    private String street;
    private String zipCode;
    private String city;


}

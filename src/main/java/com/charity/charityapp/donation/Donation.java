package com.charity.charityapp.donation;

import com.charity.charityapp.category.Category;
import com.charity.charityapp.institution.Institution;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0)
    private int quantity;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private Institution institution;

    @NotBlank
    private String street;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotEmpty
    private LocalDate pickUpDate;

    @NotEmpty
    private LocalTime pickUpTime;

    @NotBlank
    private String pickUpComment;


}

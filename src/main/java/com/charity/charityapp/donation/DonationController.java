package com.charity.charityapp.donation;

import com.charity.charityapp.category.CategoryService;
import com.charity.charityapp.institution.InstitutionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/donation")
public class DonationController {

    CategoryService categoryService;
    DonationService donationService;
    InstitutionService institutionService;

    public DonationController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {

        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryService.showAllCategories());
        model.addAttribute("institutions", institutionService.showAllInstitutions());
        return "donationForm/donationForm";
    }

    // TODO Validation, own validators
    @PostMapping("/form")
    public String getForm(@Valid Donation donation, BindingResult bindingResult) {

        donationService.save(donation);
        return "/donationForm/formConfirmation";

    }

}

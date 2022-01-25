package com.charity.charityapp.donation;

import com.charity.charityapp.category.CategoryService;
import com.charity.charityapp.institution.InstitutionService;
import com.charity.charityapp.user.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String showForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryService.showAllCategories());
        model.addAttribute("institutions", institutionService.showAllInstitutions());
        return "donationForm/donationForm";
    }

    @PostMapping("/form")
    public String getForm(@Valid Donation donation, BindingResult bindingResult) {

        donationService.save(donation);
        return "/donationForm/formConfirmation";

    }

}

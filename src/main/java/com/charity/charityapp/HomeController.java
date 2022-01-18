package com.charity.charityapp;

import com.charity.charityapp.donation.DonationService;
import com.charity.charityapp.institution.InstitutionService;
import com.charity.charityapp.user.User;
import com.charity.charityapp.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping("signUp")
    public String showForm(Model model) {
        model.addAttribute(new User());
        return "user/signUpForm";

    }

    @PostMapping("signUp")
    public String getForm(@Valid User user) {
        userService.createUser(user);
        return "redirect:/signIn";
    }

    @GetMapping("signIn")
    public String signIn() {
        return "user/signInForm";

    }

    @GetMapping("")
    public String showInstitutions(Model model){
        model.addAttribute("institutions", institutionService.showAllInstitutions());
        model.addAttribute("donations", donationService.countDonations());
        model.addAttribute("bags", donationService.countBags());
        return "home";
    }
}

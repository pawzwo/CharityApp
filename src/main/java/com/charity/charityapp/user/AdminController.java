package com.charity.charityapp.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String showMain(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        model.addAttribute("admins", userService.findAllAdmins());

        return "admin/adminMain";
    }

    @GetMapping("/add")
    public String addAdminForm(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        if (!userService.checkEnabled(currentUser)) {
            return "redirect:/admin/main";
        }
        model.addAttribute("admin", new User());
        return "admin/adminAdd";
    }

    @PostMapping("/add")
    public String addAdmin(@AuthenticationPrincipal CurrentUser currentUser, @Valid User user){
        userService.createAdmin(user);
        return "redirect:/admin/adminMain";
    }

    @GetMapping("/users")
    public String showUsers(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("nonAdmins", userService.findAllNonAdmin());
        return "/admin/adminUsers";
    }

    @GetMapping("/institutions")
    public String showInstitutions(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        return "/admin/adminInstitutions";
    }

    @GetMapping("/donations")
    public String showDonations(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        return "/admin/adminDonations";
    }


}

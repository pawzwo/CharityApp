package com.charity.charityapp.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String showMain(@AuthenticationPrincipal CurrentUser currentUser, Model model) {

        model.addAttribute("user", userService.findByEmail(currentUser.getUser().getEmail()));
        model.addAttribute("currentUser", currentUser);
        return "/user/userMain";
    }

    @PostMapping("/main")
    public String editUser(@Valid User user, @AuthenticationPrincipal CurrentUser currentUser) {
        System.out.println(user.getFirstName() + user.getLastName() + user.getEmail() + user.getStreet() + user.getCity() + user.getZipCode() + user.getPhone() + currentUser.getUser().getId());
        userService.updateUserDetails(user.getFirstName(), user.getLastName(), user.getEmail(), user.getStreet(), user.getCity(), user.getZipCode(), user.getPhone(), currentUser.getUser().getId());
        return "redirect:/user/main";
    }



}

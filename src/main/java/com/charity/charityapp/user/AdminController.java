package com.charity.charityapp.user;

import com.charity.charityapp.donation.DonationService;
import com.charity.charityapp.institution.InstitutionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final DonationService donationService;
    private final InstitutionService institutionService;

    public AdminController(UserService userService, DonationService donationService, InstitutionService institutionService) {
        this.userService = userService;
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    //Main
    @GetMapping("/main")
    public String showMain(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        model.addAttribute("admins", userService.findAllAdmins());
        model.addAttribute("currentUser", currentUser.getUser());
        model.addAttribute("user", userService.findByEmail(currentUser.getUser().getEmail()));

        return "admin/adminMain";
    }
    @PostMapping("/main")
    public String editCurrentAdmin(@Valid User user, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        userService.updateUserDetails(user.getFirstName(), user.getLastName(), user.getEmail(), user.getStreet(), user.getCity(), user.getZipCode(), user.getPhone(), currentUser.getUser().getId());
        return "redirect:/admin/main";
    }

    //Admins
    @GetMapping("/add")
    public String addAdminForm(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        model.addAttribute("admin", new User());
        model.addAttribute("user", userService.findByEmail(currentUser.getUser().getEmail()));
        return "admin/adminAdd";
    }

    @PostMapping("/add")
    public String addAdmin(@AuthenticationPrincipal CurrentUser currentUser, @Valid User user, Model model){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        userService.createAdmin(user);
        return "redirect:/admin/main";
    }

    @GetMapping("/enable/{id}")
    public String enableAdmin(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        userService.enableUser(id);
        if (userService.findById(id).getRoles().stream().anyMatch(r->r.getName().equals("ROLE_ADMIN"))){
            return "redirect:/admin/main";
        }
        return "redirect:/admin/users";
    }
    @GetMapping("/disable/{id}")
    public String disableAdmin(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        userService.disableUser(id);
        if (userService.findById(id).getRoles().stream().anyMatch(r->r.getName().equals("ROLE_ADMIN"))){
            return "redirect:/admin/main";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditAdminForm(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        model.addAttribute("admin", userService.findById(id));
        model.addAttribute("user", userService.findByEmail(currentUser.getUser().getEmail()));
        return "admin/adminEdit";
    }

    @PostMapping("/edit/{id}")
    public String editAdmin(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id, @Valid User user){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        userService.updateUserDetails(user.getFirstName(), user.getLastName(), user.getEmail(), user.getStreet(), user.getCity(), user.getZipCode(), user.getPhone(), id);

        return "redirect:/admin/main";
    }

    //Users
    @GetMapping("/users")
    public String showUsers(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        model.addAttribute("nonAdmins", userService.findAllNonAdmin());
        model.addAttribute("user", userService.findByEmail(currentUser.getUser().getEmail()));
        return "/admin/adminUsers";
    }

    @GetMapping("/user/edit/{id}")
    public String showEditUserForm(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("currentUser", userService.findByEmail(currentUser.getUser().getEmail()));
        return "admin/adminUserEdit";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id, @Valid User user){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        userService.updateUserDetails(user.getFirstName(), user.getLastName(), user.getEmail(), user.getStreet(), user.getCity(), user.getZipCode(), user.getPhone(), id);

        return "redirect:/admin/users";
    }

    //Institutions
    @GetMapping("/institutions")
    public String showInstitutions(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        model.addAttribute("user", userService.findByEmail(currentUser.getUser().getEmail()));
        model.addAttribute("institutions" , institutionService.showAllInstitutions());
        return "/admin/adminInstitutions";
    }

    @GetMapping("/institution/enable/{id}")
    public String enableInstitution(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        institutionService.enableInstitution(id);
        return "redirect:/admin/institutions";
    }
    @GetMapping("/institution/disable/{id}")
    public String disableInstitution(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
            return "redirect:/admin/main";
        }
        institutionService.disableInstitution(id);

        return "redirect:/admin/institutions";
    }

    //Donations
    @GetMapping("/donations")
    public String showDonations(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (!userService.checkEnabled(currentUser)) {
            model.addAttribute("disabled", "Your account has been disabled, please contact another Admin");
        }
        model.addAttribute("user", userService.findByEmail(currentUser.getUser().getEmail()));
        model.addAttribute("donations" , donationService.showAllDonations());
        return "/admin/adminDonations";
    }


}

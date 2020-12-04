package subscriptionhub.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import subscriptionhub.User;
import subscriptionhub.data.UserRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/profile")
public class EditProfileController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EditProfileController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String profileInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("fullname", user.getFullname());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("id", user.getId());

        return "/user-profile";
    }

    @GetMapping("/change/password")
    public String editProfileInfo(Model model) {
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "edit-password";
    }

    @PostMapping("/change/password/success")
    public String processEditProfile(@AuthenticationPrincipal User user, @Valid @ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm, Errors errors) {
        if(errors.hasErrors())
            return "edit-password";

        userRepo.save(changePasswordForm.changePassword(user, passwordEncoder, changePasswordForm));

        return "redirect:/profile";
    }
}

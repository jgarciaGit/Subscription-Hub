package subscriptionhub.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import subscriptionhub.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordForm {

    @NotNull
    @Size(min = 6, message = "*Password must be at least 6 characters*")
    private String password;

    public User changePassword(User currentUser, PasswordEncoder passwordEncoder, ChangePasswordForm changePasswordForm) {
        currentUser.setPassword(passwordEncoder.encode(changePasswordForm.getPassword()));

        return currentUser;
    }
}

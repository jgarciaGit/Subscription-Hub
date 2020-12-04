package subscriptionhub.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import subscriptionhub.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotNull
    @Size(min = 5, message = "*Username must have at least 5 characters*")
    private String username;

    @NotNull
    @Size(min = 6, message = "*Password must be at least 6 characters*")
    private String password;

    @NotNull
    @NotEmpty(message = "*Name is required*")
    private String fullname;

    @NotNull
    @NotEmpty(message = "*Email is required*")
    @Email(message = "*Invalid email*")
    private String email;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullname, email);
    }
}

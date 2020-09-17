package subscriptionhub;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
public class Subscription {

    @NotEmpty(message = "*Name is required*")
    @Size(min = 4, message = "*Name must be at least 4 characters long*")
    private String name;
    @NotEmpty(message = "*Cost is required*")
    @PositiveOrZero(message = "*Cost cannot be negative*")
    private String cost;
    @PastOrPresent(message = "*Use current billing cycle information*")
    @NotNull(message = "*You must choose a date*")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @FutureOrPresent(message = "*Use current billing cycle information*")
    @NotNull(message = "*You must choose a date*")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    @NotBlank(message = "*You must choose a billing cycle*")
    private String billingCycle;
    @NotBlank(message = "*You must choose a media type*")
    private String mediaTypes;

}

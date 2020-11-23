package subscriptionhub;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private LocalDateTime createdAt;

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

    @OneToOne(targetEntity = SubscriptionOptions.class, cascade = CascadeType.ALL)
    @Valid
    private SubscriptionOptions options;

    @ManyToOne
    private User user;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }
}

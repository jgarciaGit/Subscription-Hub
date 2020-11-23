package subscriptionhub;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Options")
@Data
public class SubscriptionOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "*You must choose a billing cycle*")
    private BillingCycle billingCycle;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "*You must choose a media type*")
    private MediaType mediaType;

    public static enum BillingCycle {
        DAILY, WEEKLY, MONTHLY, QUARTERLY, ANNUALLY
    }

    public static enum MediaType {
        MAGAZINE, BOOK, TELEVISION, RADIO, MOVIE, MUSIC, GAMING, WEBSITE, PODCAST, STREAMING, BOX, SOFTWARE, ECOMMERCE, ACCESS;
    }
}

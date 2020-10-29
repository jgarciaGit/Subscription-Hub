package subscriptionhub.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "my.subscriptions")
public class SubscriptionProperty {

    private int pageSize;
}

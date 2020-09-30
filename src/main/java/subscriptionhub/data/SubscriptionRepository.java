package subscriptionhub.data;

import org.springframework.data.repository.CrudRepository;
import subscriptionhub.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}

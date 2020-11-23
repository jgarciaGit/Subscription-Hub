package subscriptionhub.data;

import org.springframework.data.repository.CrudRepository;
import subscriptionhub.SubscriptionOptions;

public interface SubscriptionOptionsRepository extends CrudRepository<SubscriptionOptions, Long> {

    public void deleteById(Long id);
}

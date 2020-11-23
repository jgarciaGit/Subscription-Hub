package subscriptionhub.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import subscriptionhub.Subscription;
import subscriptionhub.User;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    public List<Subscription> findByUser(User user, Pageable pageable);

    public void deleteById(Long id);
}

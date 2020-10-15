package subscriptionhub.data;

import org.springframework.data.repository.CrudRepository;
import subscriptionhub.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

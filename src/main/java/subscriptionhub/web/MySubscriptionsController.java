package subscriptionhub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import subscriptionhub.User;
import subscriptionhub.data.SubscriptionRepository;
import subscriptionhub.data.UserRepository;




@Controller
@RequestMapping("mysubscriptions")
public class MySubscriptionsController {

    private final SubscriptionRepository subscriptionRepo;
    private final UserRepository userRepo;
    private SubscriptionProperty subscriptionProp;

    @Autowired
    public MySubscriptionsController(SubscriptionRepository subscriptionRepo, UserRepository userRepo, SubscriptionProperty subscriptionProp) {
        this.subscriptionRepo = subscriptionRepo;
        this.userRepo = userRepo;
        this.subscriptionProp = subscriptionProp;
    }

    @GetMapping
    public String mySubscriptions(Model model, @AuthenticationPrincipal User user) {

        Pageable pageable = PageRequest.of(0, subscriptionProp.getPageSize());

        model.addAttribute("fullname", user.getFullname());
        model.addAttribute("subscriptions", subscriptionRepo.findByUser(user, pageable));

        return "mysubscriptions";
    }
}

package subscriptionhub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import subscriptionhub.Subscription;
import subscriptionhub.User;
import subscriptionhub.data.SubscriptionRepository;
import subscriptionhub.data.UserRepository;


@Controller
@RequestMapping("mysubscriptions")
public class MySubscriptionsController {

    private final SubscriptionRepository subscriptionRepo;
    private final UserRepository userRepo;

    @Autowired
    public MySubscriptionsController(SubscriptionRepository subscriptionRepo, UserRepository userRepo) {
        this.subscriptionRepo = subscriptionRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String mySubscriptions() {
        return "mysubscriptions";
    }

    @ModelAttribute
    public void addAttributes(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("fullname", user.getFullname());
        model.addAttribute("subscriptions", subscriptionRepo.findByUser(user));
    }
}

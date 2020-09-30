package subscriptionhub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import subscriptionhub.data.SubscriptionRepository;


@Controller
@RequestMapping("mysubscriptions")
public class MySubscriptionsController {

    private final SubscriptionRepository subscriptionRepo;

    @Autowired
    public MySubscriptionsController(SubscriptionRepository subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    @GetMapping
    public String mySubscriptions() {
        return "mysubscriptions";
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("subscriptions", subscriptionRepo.findAll());
    }
}

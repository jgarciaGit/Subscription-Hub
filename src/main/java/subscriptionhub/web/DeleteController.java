package subscriptionhub.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import subscriptionhub.Subscription;
import subscriptionhub.data.SubscriptionOptionsRepository;
import subscriptionhub.data.SubscriptionRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/delete")
public class DeleteController {
    private SubscriptionRepository subscriptionRepo;
    private SubscriptionOptionsRepository optionsRepo;

    @Autowired
    public DeleteController(SubscriptionRepository subscriptionRepo, SubscriptionOptionsRepository optionsRepo) {
        this.subscriptionRepo = subscriptionRepo;
        this.optionsRepo = optionsRepo;
    }

    @GetMapping("/confirmation/{subId}")
    public String confirmDeleteSubscription(@PathVariable("subId") long id, Model model) {
        Subscription subscription = subscriptionRepo.findById(id).get();
        model.addAttribute("subscription", subscription);
        return "confirm-delete";
    }

    @PostMapping("/confirmed/{subId}")
    public String deleteSubscription(@PathVariable("subId") long id) {
        subscriptionRepo.deleteById(id);

        return "redirect:/mysubscriptions";
    }
}

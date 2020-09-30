package subscriptionhub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import subscriptionhub.Subscription;
import subscriptionhub.SubscriptionOptions;
import subscriptionhub.SubscriptionOptions.BillingCycle;
import subscriptionhub.SubscriptionOptions.MediaType;
import subscriptionhub.data.SubscriptionOptionsRepository;
import subscriptionhub.data.SubscriptionRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("add")
public class AddController {

    private final SubscriptionRepository subscriptionRepo;
    private final SubscriptionOptionsRepository optionsRepo;

    @Autowired
    public AddController(SubscriptionRepository subscriptionRepo, SubscriptionOptionsRepository optionsRepo) {
        this.subscriptionRepo = subscriptionRepo;
        this.optionsRepo = optionsRepo;
    }

    @GetMapping
    public String add() {

        return "add";
    }

    @PostMapping
    public String processAdd(@Valid @ModelAttribute("subscription") Subscription subscription, Errors errors) {
        if (errors.hasErrors())
            return "add";

        optionsRepo.save(subscription.getOptions());
        subscriptionRepo.save(subscription);

        return "redirect:/success";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        BillingCycle[] cycles = SubscriptionOptions.BillingCycle.values();
        BillingCycle[] cycle = new BillingCycle[cycles.length + 1];
        int j = 0;
        for (int i = 0; i < cycles.length + 1; i++)
        {
            if (i == 0)
            {
                cycle[i] = null;
            }
            else
            {
                cycle[i] = cycles[j];
                j++;
            }
        }
        model.addAttribute("billingCycle", cycle);
        MediaType[] types = SubscriptionOptions.MediaType.values();
        MediaType[] type = new MediaType[types.length + 1];
        j = 0;
        for (int i = 0; i < types.length + 1; i++)
        {
            if (i == 0)
            {
                type[i] = null;
            }
            else
            {
                type[i] = types[j];
                j++;
            }
        }
        model.addAttribute("mediaTypes", type);
    }

    @ModelAttribute(name = "subscription")
    public Subscription addSubscriptionToModel() {
        return new Subscription();
    }

}

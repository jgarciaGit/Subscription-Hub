package subscriptionhub.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import subscriptionhub.Subscription;
import subscriptionhub.SubscriptionOptions;
import subscriptionhub.data.SubscriptionOptionsRepository;
import subscriptionhub.data.SubscriptionRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/modify")
public class ModifySubscriptionController {

    private SubscriptionRepository subscriptionRepo;
    private SubscriptionOptionsRepository optionsRepo;

    @Autowired
    public ModifySubscriptionController(SubscriptionRepository subscriptionRepo, SubscriptionOptionsRepository optionsRepo) {
        this.subscriptionRepo = subscriptionRepo;
        this.optionsRepo = optionsRepo;
    }

    @GetMapping("/{subId}")
    public String editSubscription(@PathVariable("subId") long id, Model model) {
        Subscription subscription = subscriptionRepo.findById(id).get();
        model.addAttribute("subscription", subscription);
        return "edit-subscription";
    }

    @PostMapping("/update/{subId}/{optId}")
    public String processUpdateSubscription(@PathVariable("subId") long sid, @PathVariable("optId") long id, @Valid @ModelAttribute("subscription") Subscription subscription, Errors errors) {
        if(errors.hasErrors())
            return "edit-subscription";

        Subscription newSubscription =  subscriptionRepo.findById(sid).get();
        newSubscription.setName(subscription.getName());
        newSubscription.setCost(subscription.getCost());
        newSubscription.setStart(subscription.getStart());
        newSubscription.setEnd(subscription.getEnd());
        newSubscription.setOptions(subscription.getOptions());
        subscriptionRepo.save(newSubscription);
        optionsRepo.deleteById(id);
        log.info("Processing..." + newSubscription);
        return "redirect:/mysubscriptions";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        SubscriptionOptions.BillingCycle[] cycles = SubscriptionOptions.BillingCycle.values();
        SubscriptionOptions.BillingCycle[] cycle = new SubscriptionOptions.BillingCycle[cycles.length + 1];
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
        SubscriptionOptions.MediaType[] types = SubscriptionOptions.MediaType.values();
        SubscriptionOptions.MediaType[] type = new SubscriptionOptions.MediaType[types.length + 1];
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
}

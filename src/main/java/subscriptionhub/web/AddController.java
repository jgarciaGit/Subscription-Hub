package subscriptionhub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.ArrayUtils;
import subscriptionhub.Subscription;
import subscriptionhub.SubscriptionOptions;
import subscriptionhub.SubscriptionOptions.BillingCycle;
import subscriptionhub.SubscriptionOptions.MediaType;

import javax.validation.Valid;

@Controller
@RequestMapping("add")
public class AddController {

    @GetMapping
    public String add() {

        return "add";
    }

    @PostMapping
    public String processAdd(@Valid @ModelAttribute("subscription") Subscription subscription, Errors errors) {
        if (errors.hasErrors())
            return "add";
        return "redirect:/mysubscriptions";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        BillingCycle[] cycles = SubscriptionOptions.BillingCycle.values();
        String[] cycle = new String[cycles.length + 1];
        int j = 0;
        for (int i = 0; i < cycles.length + 1; i++)
        {
            if (i == 0)
            {
                cycle[i] = "";
            }
            else
            {
                cycle[i] = cycles[j].toString();
                j++;
            }
        }
        model.addAttribute("billingCycle", cycle);
        MediaType[] types = SubscriptionOptions.MediaType.values();
        String[] type = new String[types.length + 1];
        j = 0;
        for (int i = 0; i < types.length + 1; i++)
        {
            if (i == 0)
            {
                type[i] = "";
            }
            else
            {
                type[i] = types[j].toString();
                j++;
            }
        }
        model.addAttribute("mediaTypes", type);

        model.addAttribute("subscription", new Subscription());
    }


}

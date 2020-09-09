package subscriptionhub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import subscriptionhub.SubscriptionOptions;
import subscriptionhub.SubscriptionOptions.BillingCycle;
import subscriptionhub.SubscriptionOptions.MediaType;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class AddController {

    @GetMapping("add")
    public String add() {
        return "add";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        BillingCycle[] cycles = SubscriptionOptions.BillingCycle.values();
        model.addAttribute("billingCycle", cycles);
        MediaType[] types = SubscriptionOptions.MediaType.values();
        model.addAttribute("mediaTypes", types);
    }


}

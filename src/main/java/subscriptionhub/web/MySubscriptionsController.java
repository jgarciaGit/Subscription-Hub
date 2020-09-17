package subscriptionhub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MySubscriptionsController {

    @GetMapping("mysubscriptions")
    public String mySubscriptions() {
        return "mysubscriptions";
    }
}

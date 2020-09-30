package subscriptionhub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("success")
public class AddSuccessController {

    @GetMapping
    public String success() {
        return "success";
    }
}

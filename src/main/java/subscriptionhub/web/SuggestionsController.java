package subscriptionhub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import subscriptionhub.SubscriptionOptions;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/suggestions")
public class SuggestionsController {

    @GetMapping
    public String suggestions() {
        return "/subscription-suggestions";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("mediaTypes", SubscriptionOptions.MediaType.values());
        model.addAttribute("boxSubscriptions", boxSubscriptions());
        model.addAttribute("magazineSubscriptions", magazineSubscriptions());
        model.addAttribute("gamingSubscriptions", gamingSubscriptions());
        model.addAttribute("streamingSubscriptions", streamingSubscriptions());
        model.addAttribute("musicSubscriptions", musicSubscriptions());
    }

    private List<String> shuffleList(List<String> arr) {

        Collections.shuffle(arr);

        return arr;
    }

    private List<String> boxSubscriptions() {
        List<String> boxSubList = Arrays.asList(
            "BarkBox",
            "Universal Yums",
            "BoxyCharm",
            "Ipsy",
            "Book of the Month",
            "StitchFix",
            "KiwiCo",
            "HelloFresh",
            "Lootcrate"
        );

        return shuffleList(boxSubList);
    }

    private List<String> magazineSubscriptions() {
        List<String> magSubList = Arrays.asList(
                "National Geographic",
                "Game Informer",
                "People Magazine",
                "Time Magazine",
                "Sports Illustrated",
                "Cosmopolitan",
                "Better Homes and Gardens",
                "Reader's Digest",
                "National Geographic Kids"
        );

        return shuffleList(magSubList);
    }

    private List<String> gamingSubscriptions() {
        List<String> gameSubList = Arrays.asList(
                "PlayStation Plus",
                "PlayStation Now",
                "Xbox Live Gold",
                "Xbox Game Pass",
                "Nintendo Switch Online",
                "EA Play",
                "Ubisoft+",
                "Prime Gaming",
                "Humble Choice"
        );
        return shuffleList(gameSubList);
    }

    private List<String> streamingSubscriptions() {
        List<String> streamSubList = Arrays.asList(
                "Netflix",
                "Hulu",
                "Disney+",
                "HBO Max",
                "Apple Tv+",
                "Sling Tv",
                "fuboTv",
                "Prime Video",
                "ESPN+"
        );
        return shuffleList(streamSubList);
    }

    private List<String> musicSubscriptions() {
        List<String> musicSubList = Arrays.asList(
                "Apple Music",
                "Pandora Premium",
                "SiriusXM",
                "Spotify Premium",
                "Youtube Premium",
                "Tidal"
        );
        return shuffleList(musicSubList);
    }
}

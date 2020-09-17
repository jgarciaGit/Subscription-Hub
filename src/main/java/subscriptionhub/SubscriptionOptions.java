package subscriptionhub;

import lombok.Data;

@Data
public class SubscriptionOptions {
    private final BillingCycle billingCycle;
    private final MediaType mediaType;


    public static enum BillingCycle {
        DAILY, WEEKLY, MONTHLY, QUARTERLY, ANNUALLY;
    }

    public static enum MediaType {
         PRINT, BROADCAST, INTERNET;
    }

//    public static enum MediaType {
//        MAGAZINE, BOOK, TELEVISION, RADIO, MOVIE, MUSIC, WEBSITE, PODCAST, STREAMING;
//    }
//
//    public static enum Type {
//        BOX, SOFTWARE, ECOMMERCE, ACCESS;
//    }
}

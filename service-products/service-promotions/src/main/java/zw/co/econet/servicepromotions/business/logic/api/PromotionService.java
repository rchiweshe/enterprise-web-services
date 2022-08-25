package zw.co.econet.servicepromotions.business.logic.api;

import java.util.Locale;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

public interface PromotionService {
    PromotionsResponse createPromotion(PromotionsRequest promotionsRequest, Locale locale, String username);

    PromotionsResponse retrievePromotions(Locale locale, String username);
}

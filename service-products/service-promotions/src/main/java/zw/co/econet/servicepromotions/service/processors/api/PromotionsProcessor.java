package zw.co.econet.servicepromotions.service.processors.api;

import java.util.Locale;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

public interface PromotionsProcessor {

    public PromotionsResponse createPromotion(PromotionsRequest promotionsRequest, Locale locale, String username);

}

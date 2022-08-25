package zw.co.econet.servicepromotions.service.processors.api;

import java.util.List;
import java.util.Locale;

import zw.co.econet.servicepromotions.util.dto.PromotionDto;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

public interface PromotionsProcessor {

    public PromotionsResponse createPromotion(PromotionsRequest promotionsRequest, Locale locale, String username);

    public PromotionsResponse retrievePromotion(Locale locale, String username);


}

package zw.co.econet.servicepromotions.business.logic.impl;

import java.util.Locale;
import zw.co.econet.servicepromotions.business.logic.api.PromotionService;
import zw.co.econet.servicepromotions.business.validations.api.PromotionsServiceValidator;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

public class PromotionsServiceImpl implements PromotionService {

    private PromotionsServiceValidator promotionsServiceValidator;

    public PromotionsServiceImpl(PromotionsServiceValidator promotionsServiceValidator) {
        this.promotionsServiceValidator = promotionsServiceValidator;
    }

    @Override
    public PromotionsResponse createPromotion(PromotionsRequest promotionsRequest, Locale locale, String username) {

        boolean isRequestValid = promotionsServiceValidator.promotionsRequestIsValidForCreation(promotionsRequest);

        if (!isRequestValid) {

        }

        return null;
    }
}

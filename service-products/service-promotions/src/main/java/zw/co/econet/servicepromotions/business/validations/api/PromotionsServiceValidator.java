package zw.co.econet.servicepromotions.business.validations.api;

import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;

public interface PromotionsServiceValidator {
    public boolean promotionsRequestIsValidForCreation(PromotionsRequest promotionsRequest);
}

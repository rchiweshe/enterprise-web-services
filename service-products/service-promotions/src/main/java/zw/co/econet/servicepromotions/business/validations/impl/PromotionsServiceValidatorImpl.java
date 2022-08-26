package zw.co.econet.servicepromotions.business.validations.impl;

import zw.co.econet.servicepromotions.business.validations.api.PromotionsServiceValidator;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;

public class PromotionsServiceValidatorImpl implements PromotionsServiceValidator {

    @Override
    public boolean promotionsRequestIsValidForCreation(PromotionsRequest promotionsRequest) {

        if (promotionsRequest == null) {
            return false;
        }

        if (promotionsRequest.getDescription() == null || promotionsRequest.getEndDate() == null ||
                promotionsRequest.getName() == null || promotionsRequest.getStartDate() == null) {
            return false;
        }

        if (promotionsRequest.getName().trim().isEmpty() || promotionsRequest.getDescription().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}

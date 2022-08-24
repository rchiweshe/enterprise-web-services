package zw.co.econet.servicepromotions.service.processors.impl;

import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zw.co.econet.servicepromotions.business.logic.api.PromotionService;
import zw.co.econet.servicepromotions.service.processors.api.PromotionsProcessor;
import zw.co.econet.servicepromotions.util.dto.PromotionDto;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

public class PromotionsProcessorImpl implements PromotionsProcessor {

     private final Logger logger = LoggerFactory.getLogger(this.getClass());
     private PromotionService promotionService;

    public PromotionsProcessorImpl(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public PromotionsResponse createPromotion(PromotionsRequest promotionsRequest, Locale locale, String username) {

        logger.info("Incomming request to create a promotion : {}", promotionsRequest);

        PromotionsResponse promotionsResponse = promotionService.createPromotion(promotionsRequest, locale, username);

        logger.info("Outgoing response for creating a promotion : {}", promotionsResponse);

        return promotionsResponse;
    }

    @Override
    public PromotionsResponse retrievePromotion(Locale locale, String username) {
        logger.info("Incomming request to create all promotions");

        return null;
    }
}

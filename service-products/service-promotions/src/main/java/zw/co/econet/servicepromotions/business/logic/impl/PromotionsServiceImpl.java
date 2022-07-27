package zw.co.econet.servicepromotions.business.logic.impl;

import java.util.Locale;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import zw.co.econet.servicepromotions.business.auditables.api.PromotionsServiceAuditable;
import zw.co.econet.servicepromotions.business.logic.api.PromotionService;
import zw.co.econet.servicepromotions.business.validations.api.PromotionsServiceValidator;
import zw.co.econet.servicepromotions.domain.Promotion;
import zw.co.econet.servicepromotions.repository.PromotionsRepository;
import zw.co.econet.servicepromotions.util.dto.PromotionDto;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

public class PromotionsServiceImpl implements PromotionService {

    private PromotionsServiceValidator promotionsServiceValidator;
    private PromotionsRepository promotionsRepository;
    private ModelMapper modelMapper;
    private PromotionsServiceAuditable promotionsServiceAuditable;

    public PromotionsServiceImpl(PromotionsServiceValidator promotionsServiceValidator,
                                 PromotionsRepository promotionsRepository,
                                 ModelMapper modelMapper,
                                 PromotionsServiceAuditable promotionsServiceAuditable) {
        this.promotionsServiceValidator = promotionsServiceValidator;
        this.promotionsRepository = promotionsRepository;
        this.modelMapper = modelMapper;
        this.promotionsServiceAuditable = promotionsServiceAuditable;
    }

    @Override
    public PromotionsResponse createPromotion(PromotionsRequest promotionsRequest, Locale locale, String username) {

        boolean isRequestValid = promotionsServiceValidator.promotionsRequestIsValidForCreation(promotionsRequest);

        if (!isRequestValid) {
            return buildPromotionsResponse(400, false, "Invalid request supplied", null);
        }

        Optional<Promotion> promotionRetrieved = promotionsRepository.findByName(promotionsRequest.getName());

        if (promotionRetrieved.isPresent()) {
            return buildPromotionsResponse(400, false, "Record already exists", null);
        }

        Promotion promotionToBeSaved = modelMapper.map(promotionsRequest, Promotion.class);

        Promotion promotionSaved = promotionsServiceAuditable.createPromotion(promotionToBeSaved, locale, username);

        PromotionDto promotionDtoReturned = modelMapper.map(promotionSaved, PromotionDto.class);

        return buildPromotionsResponse(201, true, "Record successfully created", promotionDtoReturned);

    }

    private PromotionsResponse buildPromotionsResponse(int statusCode, Boolean success, String message,
                                                       PromotionDto promotionDto){
        PromotionsResponse promotionsResponse = new PromotionsResponse();
        promotionsResponse.setSuccess(success);
        promotionsResponse.setStatusCode(statusCode);
        promotionsResponse.setMessage(message);
        promotionsResponse.setPromotionDto(promotionDto);

        return promotionsResponse;
    }
}

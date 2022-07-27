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
import zw.co.econet.servicepromotions.util.enums.I18Code;
import zw.co.econet.servicepromotions.util.i18.api.MessageService;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

public class PromotionsServiceImpl implements PromotionService {

    private PromotionsServiceValidator promotionsServiceValidator;
    private PromotionsRepository promotionsRepository;
    private ModelMapper modelMapper;
    private PromotionsServiceAuditable promotionsServiceAuditable;
    private MessageService messageService;

    public PromotionsServiceImpl(PromotionsServiceValidator promotionsServiceValidator,
                                 PromotionsRepository promotionsRepository,
                                 ModelMapper modelMapper,
                                 PromotionsServiceAuditable promotionsServiceAuditable,
                                 MessageService messageService) {
        this.promotionsServiceValidator = promotionsServiceValidator;
        this.promotionsRepository = promotionsRepository;
        this.modelMapper = modelMapper;
        this.promotionsServiceAuditable = promotionsServiceAuditable;
        this.messageService = messageService;
    }

    @Override
    public PromotionsResponse createPromotion(PromotionsRequest promotionsRequest, Locale locale, String username) {

        boolean isRequestValid = promotionsServiceValidator.promotionsRequestIsValidForCreation(promotionsRequest);
        String message = "";

        if (!isRequestValid) {
            message = messageService.getMessage(I18Code.MESSAGE_PROMOTION_INVALID_REQUEST.getCode(), new String[]{},
                    locale);
            return buildPromotionsResponse(400, false, message, null);
        }

        Optional<Promotion> promotionRetrieved = promotionsRepository.findByName(promotionsRequest.getName());

        if (promotionRetrieved.isPresent()) {
            message = messageService.getMessage(I18Code.MESSAGE_PROMOTION_EXISTS.getCode(), new String[]{},
                    locale);
            return buildPromotionsResponse(400, false, message, null);
        }

        Promotion promotionToBeSaved = modelMapper.map(promotionsRequest, Promotion.class);

        Promotion promotionSaved = promotionsServiceAuditable.createPromotion(promotionToBeSaved, locale, username);

        PromotionDto promotionDtoReturned = modelMapper.map(promotionSaved, PromotionDto.class);

        message = messageService.getMessage(I18Code.MESSAGE_PROMOTION_CREATED_SUCCESSFULLY.getCode(), new String[]{},
                locale);

        return buildPromotionsResponse(201, true, message, promotionDtoReturned);

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

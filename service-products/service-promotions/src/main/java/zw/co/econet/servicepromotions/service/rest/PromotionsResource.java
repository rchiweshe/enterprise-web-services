package zw.co.econet.servicepromotions.service.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.econet.enterprise.web.services.common.utils.constants.Constants;
import zw.co.econet.servicepromotions.service.processors.api.PromotionsProcessor;
import zw.co.econet.servicepromotions.util.requests.PromotionsRequest;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

@RestController
@CrossOrigin
@RequestMapping("/ews-promotions-management/v1/prmotions")
public class PromotionsResource {

    private PromotionsProcessor promotionsProcessor;

    public PromotionsResource(PromotionsProcessor promotionsProcessor) {
        this.promotionsProcessor = promotionsProcessor;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource successfully created"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @ApiOperation(value = "Create A Promotions", response = PromotionsResponse.class)
    @PostMapping(value = "")
    public PromotionsResponse createPromotion(@Valid @RequestBody final PromotionsRequest promotionsRequest,
                                           @RequestHeader("Authorization") String authenticationToken,
                                           @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                               @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                              defaultValue = Constants.DEFAULT_LOCALE) final Locale locale)
    {
        return promotionsProcessor.createPromotion(promotionsRequest,locale, authenticationToken);
    }
}

package zw.co.econet.servicewhitelist.service.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Locale;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.econet.enterprise.web.services.common.utils.constants.Constants;
import zw.co.econet.servicewhitelist.service.processors.api.WhitelistProcessor;
import zw.co.econet.servicewhitelist.utils.dto.WhitelistDto;
import zw.co.econet.servicewhitelist.utils.response.WhiteListResponse;

@RestController
@CrossOrigin
@RequestMapping(path = "/whitelist-service/v1/whitelist")
public class WhitelistResource {

    private final WhitelistProcessor whitelistProcessor;

    public WhitelistResource(WhitelistProcessor whitelistProcessor) {
        this.whitelistProcessor = whitelistProcessor;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource successfully created"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @ApiOperation(value = "Check if number is whitelisted", response = WhiteListResponse.class)
    @GetMapping(value = "/msisdn/{msisdn}")
    public WhiteListResponse checkIfNumberIsWhitelisted(@PathVariable("msisdn") final String msisdn,
                                     @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                                                           defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return whitelistProcessor.findByMsisdn(msisdn, locale);
    }

    @ApiOperation(value = "Retrieve all whitelisted numbers", response = WhiteListResponse.class)
    @GetMapping(value = "")
    public WhiteListResponse retrieveAllWhitelistedNumbers(
                                                        @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                                        @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return whitelistProcessor.findAll(locale);
    }

    @ApiOperation(value = "Whitelist new msisdn", response = WhiteListResponse.class)
    @PostMapping(value = "/create")
    public WhiteListResponse saveMsisdn(@RequestBody final WhitelistDto whitelistDto,
                                        @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                        @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){
        return whitelistProcessor.whitelistsNumber(whitelistDto.getMsisdn(), locale);
    }

}

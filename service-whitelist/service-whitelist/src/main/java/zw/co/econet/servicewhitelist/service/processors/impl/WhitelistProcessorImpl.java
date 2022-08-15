package zw.co.econet.servicewhitelist.service.processors.impl;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zw.co.econet.servicewhitelist.business.logic.api.WhitelistService;
import zw.co.econet.servicewhitelist.service.processors.api.WhitelistProcessor;
import zw.co.econet.servicewhitelist.utils.dto.WhitelistDto;
import zw.co.econet.servicewhitelist.utils.response.ServiceResponse;
import zw.co.econet.servicewhitelist.utils.response.WhiteListResponse;

public class WhitelistProcessorImpl implements WhitelistProcessor {

    private WhitelistService whitelistService;

    public WhitelistProcessorImpl(WhitelistService whitelistService) {
        this.whitelistService = whitelistService;
    }

    Logger log = LoggerFactory.getLogger(WhitelistProcessorImpl.class.getName());

    @Override
    public WhiteListResponse whitelistsNumber(String msisd, Locale locale) {

        WhiteListResponse whiteListResponse = new WhiteListResponse();

        ServiceResponse<WhitelistDto> serviceResponse = whitelistService.saveMsisdn(msisd, locale);

        whiteListResponse.setWhitelistDto(serviceResponse.getResult());
        whiteListResponse.setMessage(serviceResponse.getMessage());
        whiteListResponse.setSuccess(serviceResponse.isSuccess());
        whiteListResponse.setStatusCode(serviceResponse.getStatusCode());

        return whiteListResponse;
    }

    @Override
    public WhiteListResponse findByMsisdn(String msisdn, Locale locale) {

        WhiteListResponse whiteListResponse = new WhiteListResponse();

        log.info("Incoming request to find whitelisted number : {}", msisdn);

        ServiceResponse<WhitelistDto> serviceResponse = whitelistService.findByMsisdn(msisdn, locale);

        whiteListResponse.setStatusCode(serviceResponse.getStatusCode());
        whiteListResponse.setSuccess(serviceResponse.isSuccess());
        whiteListResponse.setMessage(serviceResponse.getMessage());
        whiteListResponse.setWhitelistDto(serviceResponse.getResult());

        log.info("Outgoing response for search whitelisted number request {}", whiteListResponse.toString());

        return whiteListResponse;
    }

    @Override
    public WhiteListResponse  findAll(Locale locale) {

        log.info("Incoming request to retrieve all whitelisted numbers");

        WhiteListResponse whiteListResponse = new WhiteListResponse();

        ServiceResponse<WhitelistDto> serviceResponse = whitelistService.findAll(locale);

        whiteListResponse.setStatusCode(serviceResponse.getStatusCode());
        whiteListResponse.setSuccess(serviceResponse.isSuccess());
        whiteListResponse.setMessage(serviceResponse.getMessage());
        whiteListResponse.setWhitelistDtoList(serviceResponse.getList());

        log.info("Outgoing response for search all whitelisted numbers request");

        return whiteListResponse;

    }
}

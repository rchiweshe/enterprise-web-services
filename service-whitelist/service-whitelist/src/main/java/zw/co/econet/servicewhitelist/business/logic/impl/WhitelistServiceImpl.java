package zw.co.econet.servicewhitelist.business.logic.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import zw.co.econet.commons.msisdn.Msisdn;
import zw.co.econet.commons.msisdn.formatter.MsisdnFormatter;
import zw.co.econet.commons.msisdn.parser.MsisdnParser;
import zw.co.econet.enterprise.web.services.common.utils.i18.api.MessageService;
import zw.co.econet.servicewhitelist.business.auditables.api.WhitelistServiceAuditable;
import zw.co.econet.servicewhitelist.business.logic.api.WhitelistService;
import zw.co.econet.servicewhitelist.domain.Whitelist;
import zw.co.econet.servicewhitelist.utils.dto.WhitelistDto;
import zw.co.econet.servicewhitelist.utils.enums.I18Code;
import zw.co.econet.servicewhitelist.utils.mapper.WhitelistMapper;
import zw.co.econet.servicewhitelist.utils.response.ServiceResponse;
import zw.co.econet.servicewhitelist.utils.wrappers.WhitelistWrapper;

public class WhitelistServiceImpl implements WhitelistService {

    private WhitelistMapper whitelistMapper;
    private WhitelistServiceAuditable whitelistServiceAuditable;
    private MessageService messageService;
    private final MsisdnParser msisdnParser;

    public WhitelistServiceImpl(WhitelistMapper whitelistMapper, WhitelistServiceAuditable whitelistServiceAuditable,
                                MessageService messageService, MsisdnParser msisdnParser) {
        this.whitelistMapper = whitelistMapper;
        this.whitelistServiceAuditable = whitelistServiceAuditable;
        this.messageService = messageService;
        this.msisdnParser = msisdnParser;
    }

    @Override
    public ServiceResponse findByMsisdn(String msisdn, Locale locale) {

        String message = "";
        ServiceResponse serviceResponse;

        if(msisdn == null){

           message = messageService.getMessage(I18Code.MESSAGE_INVALID_MSISDN_SEARCH_REQUEST.getCode(),
                   new String[]{}, locale);
           serviceResponse =  buildResponse(400, false, message, null);
           return serviceResponse;

        }

        WhitelistWrapper whitelistWrapper = msisdnIsValid(msisdn, locale);

        if(!whitelistWrapper.isSuccess()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_MSISDN_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildResponse(400, false, message, null);
            return serviceResponse;
        }

        Optional<Whitelist> whitelistedNumber = whitelistServiceAuditable.findByMsisdn(whitelistWrapper.getMsisdn(),
                locale);

        if(!whitelistedNumber.isPresent()){

            message = messageService.getMessage(I18Code.MESSAGE_MSISDN_NOT_WHITELISTED.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildResponse(404, false, message, null);
            return serviceResponse;

        }

        WhitelistDto whitelistDto = whitelistMapper.map(whitelistedNumber.get());

        message = messageService.getMessage(I18Code.MESSAGE_WHITELISTED_MSISDN_SUCCESSFULLY_RETRIEVED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildResponse(200, true, message, whitelistDto);

        return serviceResponse;

    }

    private ServiceResponse buildResponse(int statusCode, boolean success, String message, WhitelistDto whitelistDto){


        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setMessage(message);
        serviceResponse.setSuccess(success);
        serviceResponse.setStatusCode(statusCode);
        serviceResponse.setResult(whitelistDto);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findAll(Locale locale) {

        ServiceResponse serviceResponse;
        String message = "";

        List<Whitelist> whitelistList = whitelistServiceAuditable.findAll(locale);

        if(whitelistList.isEmpty()){

            message = messageService.getMessage(I18Code.MESSAGE_WHITELISTED_NUMBERS_NOT_FOUND.getCode(),
                    new String[]{}, locale);

            List<WhitelistDto> whitelistDtoList = whitelistMapper.map(whitelistList);

            serviceResponse = new ServiceResponse();

            serviceResponse.setList(whitelistDtoList);
            serviceResponse.setStatusCode(404);
            serviceResponse.setMessage(message);
            serviceResponse.setSuccess(false);

            return serviceResponse;
        }

        List<WhitelistDto> whitelistDtoList = whitelistMapper.map(whitelistList);

        message = messageService.getMessage(I18Code.MESSAGE_WHITELISTED_NUMBERS_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = new ServiceResponse();

        serviceResponse.setList(whitelistDtoList);
        serviceResponse.setStatusCode(200);
        serviceResponse.setMessage(message);
        serviceResponse.setSuccess(true);

        return serviceResponse;

    }

    private WhitelistWrapper msisdnIsValid(String msisdn, Locale locale){

        WhitelistWrapper whitelistWrapper = new WhitelistWrapper();

        try{

            Msisdn msisdnToBeSearched = msisdnParser.parse(msisdn, locale);
            String phoneNumber = MsisdnFormatter.INTERNATIONAL.format(msisdnToBeSearched);
            whitelistWrapper.setMsisdn(phoneNumber);
            whitelistWrapper.setSuccess(true);
            return whitelistWrapper;

        }catch (Exception ex){

            whitelistWrapper.setSuccess(false);
            return whitelistWrapper;

        }

    }

    @Override
    public ServiceResponse saveMsisdn(String msisdn, Locale locale) {

        ServiceResponse serviceResponse;
        String message = "";

        if(msisdn == null){
            message = messageService.getMessage(I18Code.MESSAGE_MSISDN_REQUIRED.getCode(),
                    new String[]{}, locale);
         serviceResponse = buildResponse(400, false, message, null);
         return serviceResponse;

        }

        WhitelistWrapper whitelistWrapper = msisdnIsValid(msisdn, locale);

        if(!whitelistWrapper.isSuccess()){

            message = messageService.getMessage(I18Code.MESSAGE_INVALID_MSISDN_SUPPLIED.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildResponse(400, false, message, null);
            return serviceResponse;

        }

        String phoneNumber = whitelistWrapper.getMsisdn();

        Optional<Whitelist> whitelistSearched = whitelistServiceAuditable.findByMsisdn(phoneNumber, locale);

        if(whitelistSearched.isPresent()){
            message = messageService.getMessage(I18Code.MESSAGE_MSISDN_ALREADY_EXISTS.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildResponse(400, false, message, null);
            return serviceResponse;
        }

        Whitelist whitelistToBeSaved = new Whitelist();

        whitelistToBeSaved.setMsisdn(phoneNumber);

        Whitelist msisdnSaved = whitelistServiceAuditable.save(whitelistToBeSaved, locale);

        WhitelistDto whitelistDtoReturned = whitelistMapper.map(msisdnSaved);

        message = messageService.getMessage(I18Code.MESSAGE_MSISDN_SUCCESSFULLY_WHITELISTED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildResponse(201, true, message, whitelistDtoReturned);

        return serviceResponse;
    }
}

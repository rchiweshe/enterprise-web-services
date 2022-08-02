package zw.co.econet.enterprise.web.services.service.surveys.service.processor.impl;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyService;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.SurveyProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveyResponse;

public class SurveyProcessorImpl implements SurveyProcessor {

    private final SurveyService surveyService;

    public SurveyProcessorImpl(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public SurveyResponse save(SurveyDto surveyDto, Locale locale, String username) {

        log.info("Incomming request to create a survey : {}", surveyDto.toString());

        ServiceResponse<SurveyDto> serviceResponse = surveyService.save(surveyDto, locale, username);

        SurveyResponse surveyResponse = createSurveyDtoResponse(serviceResponse);

        log.info("Outgoing response for creating a survey : {}", surveyResponse.toString());

        return surveyResponse;
    }

    @Override
    public SurveyResponse edit(SurveyDto surveyDto, Locale locale, String username) {

        log.info("Incomming request to edit a survey : {}", surveyDto.toString());

        ServiceResponse<SurveyDto> serviceResponse = surveyService.edit(surveyDto, locale, username);

        SurveyResponse surveyResponse = createSurveyDtoResponse(serviceResponse);

        log.info("Outgoing response for editing a survey : {}", surveyResponse.toString());

        return surveyResponse;

    }

    @Override
    public SurveyResponse findById(Long id, Locale locale) {

        log.info("Incomming request to find survey by id: {}", id.toString());

        ServiceResponse<SurveyDto> serviceResponse = surveyService.findById(id, locale);

        SurveyResponse surveyResponse = createSurveyDtoResponse(serviceResponse);

        log.info("Outgoing response for retrieving survey by id: {}", surveyResponse.toString());

        return surveyResponse;
    }

    @Override
    public SurveyResponse findAll(Locale locale) {

        log.info("Incomming request to retrieve all surveys");

        ServiceResponse<SurveyDto> serviceResponse = surveyService.findAll(locale);

        SurveyResponse surveyResponse = createSurveyDtoListResponse(serviceResponse);

        log.info("Outgoing response for retrieving list of all surveys by  : {}", surveyResponse.getMessage());

        return surveyResponse;

    }

    @Override
    public SurveyResponse findBySurveyStatus(SurveyStatus surveyStatus, int page, int size, Locale locale) {

        log.info("Incomming request to find surveys by status: {}", surveyStatus.toString());


        final Pageable pageable = new QPageRequest(page, size);

        ServiceResponse<SurveyDto> serviceResponse = surveyService.findBySurveyStatus(surveyStatus,pageable, locale);

        SurveyResponse surveyResponse = createSurveyDtoPageResponse(serviceResponse);

        log.info("Outgoing response for retrieving surves by status : {}", surveyResponse.getMessage());

        return surveyResponse;

    }

    @Override
    public SurveyResponse findAll(int page, int size, Locale locale) {

        log.info("Incomming request to retrieve surveys and return as pages");


        final Pageable pageable = new QPageRequest(page, size);

        ServiceResponse<SurveyDto> serviceResponse = surveyService.findAll(pageable, locale);

        SurveyResponse surveyResponse = createSurveyDtoPageResponse(serviceResponse);

        log.info("Outgoing response for retrieving page of all surveys by  : {}", surveyResponse.getMessage());

        return surveyResponse;


    }

    @Override
    public SurveyResponse findSurveysWithMatchingName(String name, Locale locale) {

        log.info("Incoming request to retrieve all surveys with name {}", name);

        ServiceResponse<SurveyDto> serviceResponse = surveyService.findSurveysWithMatchingName(name,locale);

        SurveyResponse surveyResponse = createSurveyDtoListResponse(serviceResponse);

        log.info("Outgoing response for retrieving list of all surveys by name : {}", surveyResponse.getMessage());

        return surveyResponse;

    }

    @Override
    public SurveyResponse findBySurveyName(String name, Locale locale) {

        log.info("Incomming request to find survey by name: {}", name);

        ServiceResponse<SurveyDto> serviceResponse = surveyService.findBySurveyName(name, locale);

        SurveyResponse surveyResponse = createSurveyDtoResponse(serviceResponse);

        log.info("Outgoing response for retrieving survey by name: {}", surveyResponse.toString());

        return surveyResponse;
    }

    private SurveyResponse createSurveyDtoResponse(ServiceResponse<SurveyDto> surveyDtoServiceResponse){

        SurveyResponse surveyResponse = new SurveyResponse();
        surveyResponse.setStatusCode(surveyDtoServiceResponse.getStatusCode());
        surveyResponse.setSuccess(surveyDtoServiceResponse.isSuccess());
        surveyResponse.setSurveyDto(surveyDtoServiceResponse.getResult());
        surveyResponse.setMessage(surveyDtoServiceResponse.getMessage());

        return surveyResponse;

    }

    private SurveyResponse createSurveyDtoListResponse(ServiceResponse<SurveyDto> surveyDtoServiceResponse){

        SurveyResponse surveyResponse = new SurveyResponse();
        surveyResponse.setStatusCode(surveyDtoServiceResponse.getStatusCode());
        surveyResponse.setSuccess(surveyDtoServiceResponse.isSuccess());
        surveyResponse.setSurveyDtoList(surveyDtoServiceResponse.getList());
        surveyResponse.setMessage(surveyDtoServiceResponse.getMessage());

        return surveyResponse;

    }

    private SurveyResponse createSurveyDtoPageResponse(ServiceResponse<SurveyDto> surveyDtoServiceResponse){

        SurveyResponse surveyResponse = new SurveyResponse();
        surveyResponse.setStatusCode(surveyDtoServiceResponse.getStatusCode());
        surveyResponse.setSuccess(surveyDtoServiceResponse.isSuccess());
        surveyResponse.setSurveyDtoPage(surveyDtoServiceResponse.gettPage());
        surveyResponse.setMessage(surveyDtoServiceResponse.getMessage());

        return surveyResponse;

    }
}

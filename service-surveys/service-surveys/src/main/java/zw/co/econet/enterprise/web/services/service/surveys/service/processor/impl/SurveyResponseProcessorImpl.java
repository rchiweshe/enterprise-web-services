package zw.co.econet.enterprise.web.services.service.surveys.service.processor.impl;

import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyResponseService;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.SurveyResponseProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyResponseDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveysResponse;

public class SurveyResponseProcessorImpl implements SurveyResponseProcessor {

    private final SurveyResponseService surveyResponseService;

    public SurveyResponseProcessorImpl(SurveyResponseService surveyResponseService) {
        this.surveyResponseService = surveyResponseService;
    }

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public SurveysResponse save(SurveyResponseDto surveyResponseDto, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to create a survey response : {}", surveyResponseDto.toString());

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.save(surveyResponseDto, locale);

        surveysResponse = buildSurveysDtoResponse(serviceResponse);

        log.info("Outgoing response for creating a response to survey question: {}", surveysResponse.toString());

        return surveysResponse;
    }

    @Override
    public SurveysResponse saveAll(List<SurveyResponseDto> surveyResponseDtos, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incoming request to create a survey response : {}", surveyResponseDtos.toString());

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.saveAll(surveyResponseDtos, locale);

        surveysResponse = buildSurveysDtoListResponse(serviceResponse);

        log.info("Outgoing response for creating a response to survey question: {}", surveysResponse.toString());

        return surveysResponse;
    }

    @Override
    public SurveysResponse findById(Long id, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to find a survey by id : {}", id.toString());

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.findById(id, locale);

        surveysResponse = buildSurveysDtoResponse(serviceResponse);

        log.info("Outgoing response for retrieving a survey response by id : {}", surveysResponse.toString());

        return surveysResponse;

    }

    @Override
    public SurveysResponse findBySuveryId(String id, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to retrieve responses for a survey by survey ID : {}", id.toString());

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.findBySuveryId(id, locale);

        surveysResponse = buildSurveysDtoListResponse(serviceResponse);

        log.info("Outgoing response for retrieving a responses to a specific survey: {}", surveysResponse.getMessage());

        return surveysResponse;
    }

    @Override
    public SurveysResponse findByQuestion(Long questionId, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to retrieve responses for a survey by question ID : {}", questionId.toString());

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.findByQuestion(questionId, locale);

        surveysResponse = buildSurveysDtoListResponse(serviceResponse);

        log.info("Outgoing response for retrieving a responses to a specific survey question: {}", surveysResponse.getMessage());

        return surveysResponse;
    }

    @Override
    public SurveysResponse findByMsisdn(String msisdn, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to retrieve responses for a survey by msisdn : {}", msisdn.toString());

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.findByMsisdn(msisdn, locale);

        surveysResponse = buildSurveysDtoListResponse(serviceResponse);

        log.info("Outgoing response for retrieving a responses using msisdn: {}", surveysResponse.getMessage());

        return surveysResponse;

    }

    @Override
    public SurveysResponse findAll(int page, int size, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to retrieve all survey responses");

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.findAll(page, size, locale);

        surveysResponse = buildSurveysDtoPageResponse(serviceResponse);

        log.info("Outgoing response for retrieving all survey responses", surveysResponse.getMessage());

        return surveysResponse;

    }

    @Override
    public SurveysResponse findByMsisdnAndSuveryId(String msisdn, String surveyId, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to retrieve responses for a survey by msisdn : {} and survey id : {}", msisdn.toString(), surveyId);

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.findByMsisdn(msisdn, locale);

        surveysResponse = buildSurveysDtoListResponse(serviceResponse);

        log.info("Outgoing response for retrieving a responses using msisdn and survey id: {}", surveysResponse.getMessage());

        return surveysResponse;
    }

    @Override
    public SurveysResponse findByMsisdnAndQuestion(String msisdn, String questionId, Locale locale) {

        SurveysResponse surveysResponse = new SurveysResponse();

        log.info("Incomming request to retrieve responses for a survey by msisdn : {} and question id : {}", msisdn.toString(), questionId);

        ServiceResponse<SurveyResponseDto> serviceResponse = surveyResponseService.findByMsisdn(msisdn, locale);

        surveysResponse = buildSurveysDtoListResponse(serviceResponse);

        log.info("Outgoing response for retrieving a responses using msisdn and question id: {}", surveysResponse.getMessage());

        return surveysResponse;

    }

    private SurveysResponse buildSurveysDtoResponse(ServiceResponse<SurveyResponseDto> surveyResponseDtoServiceResponse) {

        SurveysResponse surveysResponse = new SurveysResponse();

        surveysResponse.setMessage(surveyResponseDtoServiceResponse.getMessage());
        surveysResponse.setStatusCode(surveyResponseDtoServiceResponse.getStatusCode());
        surveysResponse.setSuccess(surveyResponseDtoServiceResponse.isSuccess());
        surveysResponse.setSurveyResponseDto(surveyResponseDtoServiceResponse.getResult());

        return surveysResponse;

    }

    private SurveysResponse buildSurveysDtoListResponse(ServiceResponse<SurveyResponseDto> surveyResponseDtoServiceResponse) {

        SurveysResponse surveysResponse = new SurveysResponse();

        surveysResponse.setMessage(surveyResponseDtoServiceResponse.getMessage());
        surveysResponse.setStatusCode(surveyResponseDtoServiceResponse.getStatusCode());
        surveysResponse.setSuccess(surveyResponseDtoServiceResponse.isSuccess());
        surveysResponse.setSurveyResponseDtos(surveyResponseDtoServiceResponse.getList());

        return surveysResponse;

    }

    private SurveysResponse buildSurveysDtoPageResponse(ServiceResponse<SurveyResponseDto> surveyResponseDtoServiceResponse) {

        SurveysResponse surveysResponse = new SurveysResponse();

        surveysResponse.setMessage(surveyResponseDtoServiceResponse.getMessage());
        surveysResponse.setStatusCode(surveyResponseDtoServiceResponse.getStatusCode());
        surveysResponse.setSuccess(surveyResponseDtoServiceResponse.isSuccess());
        surveysResponse.setSurveyResponseDtoPage(surveyResponseDtoServiceResponse.gettPage());

        return surveysResponse;

    }


}

package zw.co.econet.enterprise.web.services.service.surveys.service.processor.impl;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.QuestionService;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.QuestionProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.QuestionDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionEditRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.QuestionResponse;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;

public class QuestionProcessorImpl implements QuestionProcessor {

    private final QuestionService questionService;

    public QuestionProcessorImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    Logger log = LoggerFactory.getLogger(QuestionProcessorImpl.class.getName());


    @Override
    public QuestionResponse save(QuestionRequest questionRequest, Locale locale, String username) {

        log.info("Question Request in: {}", questionRequest);

        ServiceResponse<QuestionDto> serviceResponse = questionService.save(questionRequest, locale, username);

        QuestionResponse response = new QuestionResponse();

        response.setSuccess(serviceResponse.isSuccess());
        response.setMessage(serviceResponse.getMessage());
        response.setQuestionDto(serviceResponse.getResult());
        log.info("Response out: {}", response);

        return response;
    }

    @Override
    public QuestionResponse edit(QuestionEditRequest questionEditRequest, Locale locale, String username) {

        log.info("Update Question Request in : {}", questionEditRequest);

        ServiceResponse<QuestionDto> serviceResponse = questionService.edit(questionEditRequest, locale, username);

        QuestionResponse response = new QuestionResponse();

        response.setSuccess(serviceResponse.isSuccess());
        response.setMessage(serviceResponse.getMessage());
        response.setQuestionDto(serviceResponse.getResult());

        log.info("Response out: {}", response);

        return response;
    }

    @Override
    public QuestionResponse findById(Long id, Locale locale) {

        log.info("Question Search Request in: {}", id);

        ServiceResponse<QuestionDto> serviceResponse = questionService.findById(id, locale);

        QuestionResponse response = new QuestionResponse();

        response.setSuccess(serviceResponse.isSuccess());
        response.setMessage(serviceResponse.getMessage());
        response.setQuestionDto(serviceResponse.getResult());

        log.info("Response out: {}", response);

        return response;
    }

    @Override
    public QuestionResponse findAll(Locale locale) {

        log.info("Question Search Request to Find All QUESTIONS");

        ServiceResponse<QuestionDto> serviceResponse = questionService.findAll(locale);

        QuestionResponse response = new QuestionResponse();

        response.setSuccess(true);
        response.setMessage(serviceResponse.getMessage());
        response.setQuestionDtoList(serviceResponse.getList());

        log.info("Response out: {}", response);

        return response;
    }

    @Override
    public QuestionResponse findByRecordStatus(RecordStatus recordStatus, int page, int size, Locale locale) {

        log.info("Question Search Request in: {}", recordStatus);

        final Pageable pageable = new QPageRequest(page, size);

        ServiceResponse<QuestionDto> serviceResponse =
                questionService.findByRecordStatus(recordStatus,pageable, locale);

        QuestionResponse response = new QuestionResponse();

        response.setSuccess(serviceResponse.isSuccess());
        response.setMessage(serviceResponse.getMessage());
        response.setQuestionDtoList(serviceResponse.getList());

        log.info("Response out: {}", response);

        return response;
    }

    @Override
    public QuestionResponse findAll(int page, int size, Locale locale) {

        log.info("Question Search Request in: {}");

        final Pageable pageable = new QPageRequest(page, size);

        ServiceResponse<Question> serviceResponse = questionService.findAll(pageable, locale);

        QuestionResponse response = new QuestionResponse();

        response.setSuccess(serviceResponse.isSuccess());
        response.setMessage(serviceResponse.getMessage());
        response.setQuestionPage(serviceResponse.gettPage());

        log.info("Response out: {}", response);

        return response;


    }

    @Override
    public QuestionResponse findQuestionsBySurveyId(String surveyId, Locale locale) {

        log.info("Question Search Request in: {}", surveyId);

        ServiceResponse<QuestionDto> serviceResponse = questionService.findQuestionsBySurvey(surveyId, locale);

        QuestionResponse response = new QuestionResponse();

        response.setSuccess(serviceResponse.isSuccess());
        response.setMessage(serviceResponse.getMessage());
        response.setQuestionDtoList(serviceResponse.getList());

        log.info("Response out: {}", response);

        return response;
    }
}

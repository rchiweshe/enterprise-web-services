package zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import zw.co.econet.commons.msisdn.Msisdn;
import zw.co.econet.commons.msisdn.formatter.MsisdnFormatter;
import zw.co.econet.commons.msisdn.parser.MsisdnParser;
import zw.co.econet.enterprise.web.services.common.utils.i18.api.MessageService;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.SurveyResponseServiceAuditable;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.SurveyServiceAuditable;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyResponseService;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyResponse;
import zw.co.econet.enterprise.web.services.service.surveys.repository.QuestionRepository;
import zw.co.econet.enterprise.web.services.service.surveys.util.I18Code;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyResponseDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;
import zw.co.econet.enterprise.web.services.service.surveys.util.wrapper.MsisdnWrapper;

public class SurveyResponseServiceImpl implements SurveyResponseService {

    private final ModelMapper modelMapper;
    private final SurveyResponseServiceAuditable surveyResponseServiceAuditable;
    private final MessageService messageService;
    private final QuestionRepository questionRepository;
    private final SurveyServiceAuditable surveyServiceAuditable;
    private MsisdnParser msisdnParser;

    public SurveyResponseServiceImpl(ModelMapper modelMapper,
                                     SurveyResponseServiceAuditable surveyResponseServiceAuditable,
                                     MessageService messageService,
                                     QuestionRepository questionRepository,
                                     SurveyServiceAuditable surveyServiceAuditable,
                                     MsisdnParser msisdnParser) {
        this.modelMapper = modelMapper;
        this.surveyResponseServiceAuditable = surveyResponseServiceAuditable;
        this.messageService = messageService;
        this.questionRepository = questionRepository;
        this.surveyServiceAuditable = surveyServiceAuditable;
        this.msisdnParser = msisdnParser;
    }

    @Override
    public ServiceResponse save(SurveyResponseDto surveyResponseDto, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();
        String message = "";

        if (!surveyResponseIsValid(surveyResponseDto)) {
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_INVALID_CREATION_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        Long questionId = (long)Double.parseDouble(surveyResponseDto.getQuestionId());

        Optional<Question> questionSearched = questionRepository.findById(questionId);

        if (!questionSearched.isPresent()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_QUESTION_SELECTED.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;

        }

        List<SurveyResponse> userResponse = surveyResponseServiceAuditable.findByMsisdnAndQuestionAndSuveryId(
                surveyResponseDto.getMsisdn(), questionSearched.get(),surveyResponseDto.getSuveryId());

        if (userResponse.size() > 0) {
            message = messageService.getMessage(I18Code.MESSAGE_USER_RESPONSE_ALREADY_CAPTURED.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        Long surveyId = Long.valueOf(surveyResponseDto.getSuveryId());

        Optional<Survey> surveySearched = surveyServiceAuditable.findById(surveyId, locale);

        if (!surveySearched.isPresent()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_SURVEY_SELECTED.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;

        }

        SurveyResponse surveyResponse = modelMapper.map(surveyResponseDto, SurveyResponse.class);

        surveyResponse.setQuestion(questionSearched.get());

        SurveyResponse surveyResponseSaved = surveyResponseServiceAuditable.save(surveyResponse, locale);

        SurveyResponseDto surveyResponseDtoReturned = modelMapper.map(surveyResponseSaved, SurveyResponseDto.class);

        message = messageService.getMessage(I18Code.MESSAGE_RESPONSE_SAVED_SUCCESS.getCode(), new String[]{}, locale);

        serviceResponse = buildSurveyResponse(200, true, message, surveyResponseDtoReturned);

        return serviceResponse;
    }

    @Override
    public ServiceResponse saveAll(List<SurveyResponseDto> surveyResponseDto, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();
        String message = "";

        List<SurveyResponse> surveyResponses = convertSurveyResponseDtoToSurveyResponseEntity(surveyResponseDto);

        List<SurveyResponse> surveyResponsesSaved = surveyResponseServiceAuditable.saveAll(surveyResponses, locale);

        List<SurveyResponseDto> surveyResponsesDtoReturned = convertSurveyResponseToSurveyResponseDto(surveyResponsesSaved);

        message = messageService.getMessage(I18Code.MESSAGE_RESPONSE_SAVED_SUCCESS.getCode(), new String[]{}, locale);

        serviceResponse = buildSurveyListResponse(200, true, message, surveyResponsesDtoReturned);

        return serviceResponse;
    }

    @Override
    public ServiceResponse findById(Long id, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();
        String message = "";

        if (id == null) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_SURVEY_RESPONSE_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        Optional<SurveyResponse> surveyResponseSearched = surveyResponseServiceAuditable.findById(id, locale);

        if (!surveyResponseSearched.isPresent()) {

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(404, false, message, null);
            return serviceResponse;

        }

        SurveyResponseDto surveyResponseDto = modelMapper.map(surveyResponseSearched.get(), SurveyResponseDto.class);

        surveyResponseDto.setQuestionId(surveyResponseSearched.get().getQuestion().getId().toString());

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyResponse(200, true, message, surveyResponseDto);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findBySuveryId(String id, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();
        String message = "";

        if (id == null) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_SURVEY_RESPONSE_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        List<SurveyResponse> surveyResponseList = surveyResponseServiceAuditable.findBySuveryId(id, locale);

        if (surveyResponseList.isEmpty()) {

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(404, false, message, null);
            return serviceResponse;

        }

        List<SurveyResponseDto> surveyResponseDtos = buildSurveyResponseDtoList(surveyResponseList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSES_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyListResponse(200, true, message, surveyResponseDtos);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findByQuestion(Long questionId, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        if (questionId == null) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_SURVEY_RESPONSE_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        Optional<Question> questionSearched = questionRepository.findById(questionId);

        if (!questionSearched.isPresent()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_QUESTION_SELECTED.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        List<SurveyResponse> surveyResponseList = surveyResponseServiceAuditable.findByQuestion(questionSearched.get(), locale);

        if (surveyResponseList.isEmpty()) {

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(404, false, message, null);
            return serviceResponse;

        }

        List<SurveyResponseDto> surveyResponseDtos = buildSurveyResponseDtoList(surveyResponseList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSES_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyListResponse(200, true, message, surveyResponseDtos);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findByMsisdn(String msisdn, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();
        String message = "";

        if (msisdn == null) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_SURVEY_RESPONSE_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        MsisdnWrapper msisdnWrapper = validateMsisdn(msisdn, locale);

        if (!msisdnWrapper.isMsisdnIsValid()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_MSISDN.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        String phoneNumber = msisdnWrapper.getMsisdn();

        List<SurveyResponse> surveyResponseList = surveyResponseServiceAuditable.findByMsisdn(phoneNumber, locale);

        if (surveyResponseList.isEmpty()) {

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(404, false, message, null);
            return serviceResponse;

        }

        List<SurveyResponseDto> surveyResponseDtos = buildSurveyResponseDtoList(surveyResponseList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSES_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyListResponse(200, true, message, surveyResponseDtos);

        return serviceResponse;


    }

    @Override
    public ServiceResponse findAll(int page, int size, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        final Pageable pageable = new QPageRequest(page, size);

        Page<SurveyResponse> surveyResponsePage = surveyResponseServiceAuditable.findAll(pageable, locale);

        Page<SurveyResponseDto> surveyResponseDtoPage = convertSurveyResponseEntityToSurveyResponseDto(surveyResponsePage);

        if (surveyResponsePage.getContent().size() == 0) {

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyPageResponse(404, false, message, surveyResponseDtoPage);
            return serviceResponse;

        }


        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSES_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyPageResponse(200, true, message, surveyResponseDtoPage);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findByMsisdnAndSuveryId(String msisdn, String surveyId, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();
        String message = "";

        if (msisdn == null || surveyId == null || msisdn.trim().isEmpty() || surveyId.trim().isEmpty()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_SURVEY_RESPONSE_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        MsisdnWrapper msisdnWrapper = validateMsisdn(msisdn, locale);

        if (!msisdnWrapper.isMsisdnIsValid()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_MSISDN.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        String phoneNumber = msisdnWrapper.getMsisdn();

        List<SurveyResponse> surveyResponseList = surveyResponseServiceAuditable.findByMsisdnAndSuveryId(phoneNumber, surveyId, locale);

        if (surveyResponseList.isEmpty()) {

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(404, false, message, null);
            return serviceResponse;

        }

        List<SurveyResponseDto> surveyResponseDtos = buildSurveyResponseDtoList(surveyResponseList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSES_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyListResponse(200, true, message, surveyResponseDtos);

        return serviceResponse;


    }

    @Override
    public ServiceResponse findByMsisdnAndQuestion(String msisdn, String questionId, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        if (questionId == null) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_SURVEY_RESPONSE_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        Optional<Question> questionSearched = questionRepository.findById(Long.valueOf(questionId));

        if (!questionSearched.isPresent()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_QUESTION_SELECTED.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        MsisdnWrapper msisdnWrapper = validateMsisdn(msisdn, locale);

        if (!msisdnWrapper.isMsisdnIsValid()) {
            message = messageService.getMessage(I18Code.MESSAGE_INVALID_MSISDN.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(400, false, message, null);
            return serviceResponse;
        }

        String phoneNumber = msisdnWrapper.getMsisdn();

        List<SurveyResponse> surveyResponseList = surveyResponseServiceAuditable.findByMsisdnAndQuestion(phoneNumber, questionSearched.get(), locale);

        if (surveyResponseList.isEmpty()) {

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyResponse(404, false, message, null);
            return serviceResponse;

        }

        List<SurveyResponseDto> surveyResponseDtos = buildSurveyResponseDtoList(surveyResponseList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_RESPONSES_RETRIEVED_SUCCESS.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyListResponse(200, true, message, surveyResponseDtos);

        return serviceResponse;


    }


    private Boolean surveyResponseIsValid(SurveyResponseDto surveyResponseDto) {

        if (surveyResponseDto == null) {
            return false;
        }

        if (surveyResponseDto.getAnswer() == null || surveyResponseDto.getMsisdn() == null ||
                surveyResponseDto.getSuveryId() == null || surveyResponseDto.getQuestionId() == null) {
            return false;
        }

        if (surveyResponseDto.getAnswer().isEmpty() || surveyResponseDto.getMsisdn().isEmpty() ||
                surveyResponseDto.getSuveryId().isEmpty() || surveyResponseDto.getQuestionId().isEmpty()) {
            return false;
        }

        return true;

    }

    private Page<SurveyResponseDto> convertSurveyResponseEntityToSurveyResponseDto(Page<SurveyResponse> surveyResponses) {

        List<SurveyResponse> surveyResponseList = surveyResponses.getContent();
        List<SurveyResponseDto> surveyResponseDtos = new ArrayList<>();

        if (surveyResponseList != null) {
            for (SurveyResponse surveyResponse : surveyResponseList) {
                SurveyResponseDto surveyResponseDto = modelMapper.map(surveyResponse, SurveyResponseDto.class);
                surveyResponseDto.setQuestionId(surveyResponse.getQuestion().getId().toString());
                surveyResponseDtos.add(surveyResponseDto);
            }
        }

        int page = surveyResponses.getNumber();
        int size = surveyResponses.getSize();

        size = size <= 0 ? 10 : size;

        Pageable pageableSurvey = new QPageRequest(page, size);

        return new PageImpl<SurveyResponseDto>(surveyResponseDtos, pageableSurvey, surveyResponses.getTotalElements());

    }

    private List<SurveyResponseDto> convertSurveyResponseToSurveyResponseDto(List<SurveyResponse> surveyResponses) {

        List<SurveyResponseDto> surveyResponseDtoList = new ArrayList<>();

        if (surveyResponses != null) {
            for (SurveyResponse surveyResponse : surveyResponses) {
                SurveyResponseDto surveyResponseDto = modelMapper.map(surveyResponse, SurveyResponseDto.class);
                surveyResponseDto.setQuestionId(surveyResponse.getQuestion().getId().toString());
                surveyResponseDtoList.add(surveyResponseDto);
            }
        }
        return surveyResponseDtoList;

    }

    private List<SurveyResponse> convertSurveyResponseDtoToSurveyResponseEntity(List<SurveyResponseDto> surveyResponseDtos) {

        List<SurveyResponse> surveyResponseList = new ArrayList<>();

        if (surveyResponseDtos != null) {
            for (SurveyResponseDto surveyResponseDto : surveyResponseDtos) {
                SurveyResponse surveyResponse = modelMapper.map(surveyResponseDto, SurveyResponse.class);
                Optional<Question> question = questionRepository.findById(Long.parseLong(surveyResponseDto.getQuestionId()));
                question.ifPresent(surveyResponse::setQuestion);
                surveyResponseList.add(surveyResponse);
            }
        }
        return surveyResponseList;

    }

    private ServiceResponse buildSurveyResponse(int statusCode, Boolean success, String message, SurveyResponseDto surveyResponseDto) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(success);
        serviceResponse.setMessage(message);
        serviceResponse.setStatusCode(statusCode);
        serviceResponse.setResult(surveyResponseDto);

        return serviceResponse;
    }

    private ServiceResponse buildSurveyListResponse(int statusCode, Boolean success, String message, List<SurveyResponseDto> surveyResponseDtoList) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(success);
        serviceResponse.setMessage(message);
        serviceResponse.setStatusCode(statusCode);
        serviceResponse.setList(surveyResponseDtoList);

        return serviceResponse;
    }

    private ServiceResponse buildSurveyPageResponse(int statusCode, Boolean success, String message, Page<SurveyResponseDto> surveyResponseDtoPage) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(success);
        serviceResponse.setMessage(message);
        serviceResponse.setStatusCode(statusCode);
        serviceResponse.settPage(surveyResponseDtoPage);

        return serviceResponse;
    }


    private List<SurveyResponseDto> buildSurveyResponseDtoList(List<SurveyResponse> surveyResponseList) {

        List<SurveyResponseDto> surveyResponseDtoList = new ArrayList<>();

        for (SurveyResponse surveyResponse : surveyResponseList) {

            SurveyResponseDto surveyResponseDto = modelMapper.map(surveyResponse, SurveyResponseDto.class);
            surveyResponseDto.setQuestionId(surveyResponse.getQuestion().getId().toString());

            surveyResponseDtoList.add(surveyResponseDto);

        }

        return surveyResponseDtoList;

    }

    private MsisdnWrapper validateMsisdn(String msisdn, Locale locale) {

        MsisdnWrapper msisdnWrapper = new MsisdnWrapper();

        try {
            Msisdn msisdnToBeSearched = msisdnParser.parse(msisdn, locale);

            String phoneNumber = MsisdnFormatter.INTERNATIONAL.format(msisdnToBeSearched);

            msisdnWrapper.setMsisdn(phoneNumber);

            msisdnWrapper.setMsisdnIsValid(true);

        } catch (Exception ex) {

            msisdnWrapper.setMsisdnIsValid(false);

            msisdnWrapper.setMessage(ex.getMessage());

        }

        return msisdnWrapper;

    }


}

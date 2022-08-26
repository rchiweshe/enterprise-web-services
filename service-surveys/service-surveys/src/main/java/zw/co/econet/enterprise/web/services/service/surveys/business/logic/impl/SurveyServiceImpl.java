package zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.common.utils.i18.api.MessageService;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.SurveyServiceAuditable;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyService;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Answers;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyRepository;
import zw.co.econet.enterprise.web.services.service.surveys.util.I18Code;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerOptionsDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.QuestionDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.CreateSurveyRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;

public class SurveyServiceImpl implements SurveyService {

    private final SurveyServiceAuditable surveyServiceAuditable;
    private final ModelMapper modelMapper;
    private final MessageService messageService;
    private final SurveyRepository surveyRepository;
    private final ModelMapper mapper;


    public SurveyServiceImpl(SurveyServiceAuditable surveyServiceAuditable,
                             ModelMapper modelMapper,
                             MessageService messageService,
                             SurveyRepository surveyRepository,
                             ModelMapper mapper) {
        this.surveyServiceAuditable = surveyServiceAuditable;
        this.modelMapper = modelMapper;
        this.messageService = messageService;
        this.surveyRepository = surveyRepository;
        this.mapper = mapper;
    }

    @Override
    public ServiceResponse save(CreateSurveyRequest createSurveyRequest, Locale locale, String username) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        if(!validateSurveyCreationRequest(createSurveyRequest)){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_INVALID_REQUEST.getCode(),
                    new String[]{}, locale);
           serviceResponse = buildSurveyDtoserviceResponse(400, false, message, null);
           return serviceResponse;

        }

        Optional<Survey> surveySearched = surveyRepository.findByName(createSurveyRequest.getName());

        if(surveySearched.isPresent()){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_WITH_NAME_ALREADY_EXISTS.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(400, false, message, null);
            return serviceResponse;
        }

        Survey survey = modelMapper.map(createSurveyRequest, Survey.class);

        Survey surveySaved = surveyServiceAuditable.save(survey,locale, username);

        SurveyDto surveyDtoReturned = modelMapper.map(surveySaved, SurveyDto.class);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_SUCCESSFULLY_CREATED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoserviceResponse(200, true, message, surveyDtoReturned);

        return serviceResponse;

    }

    @Override
    public ServiceResponse edit(SurveyDto surveyDto, Locale locale, String username) {

        ServiceResponse serviceResponse = new ServiceResponse();
        String message = null;

        if(!validateSurveyCreationRequest(surveyDto) || surveyDto.getId() == null){

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_INVALID_EDIT_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(400, false, message, null);
            return serviceResponse;
        }

        Optional<Survey> surveyRetrieved = surveyServiceAuditable.findById(surveyDto.getId(), locale);

        if(!surveyRetrieved.isPresent()){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(404, false, message, null);
            return serviceResponse;

        }

        String name = surveyDto.getName() != null ? surveyDto.getName() : surveyRetrieved.get().getName();

        String description = surveyDto.getDescription() != null ? surveyDto.getDescription() : surveyRetrieved.get().getDescription();

        SurveyStatus status = surveyDto.getSurveyStatus() != null ? surveyDto.getSurveyStatus() : surveyRetrieved.get().getSurveyStatus();

        RecordStatus recordStatus = surveyDto.getRecordStatus() != null ? surveyDto.getRecordStatus() : surveyRetrieved.get().getRecordStatus();

        surveyRetrieved.get().setName(name);

        surveyRetrieved.get().setDescription(description);

        surveyRetrieved.get().setRecordStatus(recordStatus);

        surveyRetrieved.get().setSurveyStatus(status);

        Survey surveyToBeSaved = surveyServiceAuditable.save(surveyRetrieved.get(), locale, username);

        SurveyDto surveyDtoReturned = modelMapper.map(surveyToBeSaved, SurveyDto.class);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_SUCCESSFULLY_EDITED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoserviceResponse(200, true, message, surveyDtoReturned);

        return serviceResponse;

    }

    private boolean validateSurveyCreationRequest(SurveyDto surveyDto) {
        if (surveyDto.getId() == null) {
            return false;
        }
        return true;
    }

    @Override
    public ServiceResponse findById(Long id, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        if(id == null){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_INVALID_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(400, false, message, null);
            return serviceResponse;
        }

        Optional<Survey> surveySearched = surveyServiceAuditable.findById(id, locale);

        if(!surveySearched.isPresent()){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(404, false, message, null);
            return serviceResponse;
        }

        SurveyDto surveyDtoReturned = modelMapper.map(surveySearched.get(), SurveyDto.class);

        List<QuestionDto> questionDtoList = buildListOfQuestionsDto(surveySearched);

        surveyDtoReturned.setQuestionDto(questionDtoList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_SUCCESSFULLY_RETRIEVED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoserviceResponse(200, true, message, surveyDtoReturned);

        return serviceResponse;
    }

    @Override
    public ServiceResponse findAll(Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        List<Survey> surveyList = surveyServiceAuditable.findAll(locale);

        if(surveyList.isEmpty()){
            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_SURVEY_NOT_FOUND.getCode(),
                    new String[]{}, locale));
            serviceResponse = buildSurveyDtoListserviceResponse(404, false, message, null);
            return serviceResponse;
        }

        List<SurveyDto> surveyDtoListReturned = surveyDtoListFromSurveyList(surveyList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEYS_SUCCESSFULLY_RETRIEVED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoListserviceResponse(200, true, message, surveyDtoListReturned);

        return serviceResponse;
    }

    @Override
    public ServiceResponse findBySurveyStatus(SurveyStatus surveyStatus, Pageable pageable, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        if(surveyStatus == null){
            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_SURVEY_INVALID_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale));
            serviceResponse = buildSurveyDtoserviceResponse(400, false, message, null);
            return serviceResponse;
        }

        Page<Survey> surveyPage = surveyServiceAuditable.findBySurveyStatus(surveyStatus, pageable, locale);

        if(surveyPage.getContent().size() == 0){
            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_SURVEY_NOT_FOUND.getCode(),
                    new String[]{}, locale));
            serviceResponse = buildSurveyDtoserviceResponse(404, false, message, null);
            return serviceResponse;
        }

        Page<SurveyDto> surveyDtoPage = convertSurveyEntityToSurveyDto(surveyPage);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEYS_SUCCESSFULLY_RETRIEVED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoPageserviceResponse(200, true, message, surveyDtoPage);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findAll(Pageable pageable, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        Page<Survey> surveyPage = surveyServiceAuditable.findAll(pageable, locale);

        if(surveyPage.getContent().isEmpty()){
            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_SURVEY_NOT_FOUND.getCode(),
                    new String[]{}, locale));
            serviceResponse = buildSurveyDtoserviceResponse(404, false, message, null);
            return serviceResponse;
        }

        Page<SurveyDto> surveyDtoPage = convertSurveyEntityToSurveyDto(surveyPage);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEYS_SUCCESSFULLY_RETRIEVED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoPageserviceResponse(200, true, message, surveyDtoPage);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findSurveysWithMatchingName(String name, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        if(name == null){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_INVALID_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(400, false, message, null);
            return serviceResponse;
        }

        List<Survey> surveyList = surveyServiceAuditable.findSurveysWithMatchingName(name,locale);

        if(surveyList.isEmpty()){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoListserviceResponse(404, false, message, null);
            return serviceResponse;
        }

        List<SurveyDto> surveyDtoListReturned = surveyDtoListFromSurveyList(surveyList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEYS_SUCCESSFULLY_RETRIEVED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoListserviceResponse(200, true, message, surveyDtoListReturned);

        return serviceResponse;

    }

    @Override
    public ServiceResponse findBySurveyName(String name, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        String message = "";

        if(name == null){

            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_INVALID_SEARCH_REQUEST.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(400, false, message, null);
            return serviceResponse;
        }

        Optional<Survey> surveySearched = surveyServiceAuditable.findBySurveyName(name, locale);

        if(!surveySearched.isPresent()){
            message = messageService.getMessage(I18Code.MESSAGE_SURVEY_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            serviceResponse = buildSurveyDtoserviceResponse(404, false, message, null);
            return serviceResponse;
        }

        SurveyDto surveyDtoReturned = modelMapper.map(surveySearched.get(), SurveyDto.class);

        List<QuestionDto> questionDtoList = buildListOfQuestionsDto(surveySearched);

        surveyDtoReturned.setQuestionDto(questionDtoList);

        message = messageService.getMessage(I18Code.MESSAGE_SURVEY_SUCCESSFULLY_RETRIEVED.getCode(),
                new String[]{}, locale);

        serviceResponse = buildSurveyDtoserviceResponse(200, true, message, surveyDtoReturned);

        return serviceResponse;

    }

    private ServiceResponse buildSurveyDtoserviceResponse(int statusCode, Boolean success, String message, SurveyDto surveyDto){

        ServiceResponse serviceResponse = new ServiceResponse();

        serviceResponse.setStatusCode(statusCode);
        serviceResponse.setMessage(message);
        serviceResponse.setSuccess(success);
        serviceResponse.setResult(surveyDto);

        return serviceResponse;

    }

    private ServiceResponse buildSurveyDtoListserviceResponse(int statusCode, Boolean success, String message, List<SurveyDto> surveyDtoList){

        ServiceResponse serviceResponse = new ServiceResponse();

        serviceResponse.setStatusCode(statusCode);
        serviceResponse.setMessage(message);
        serviceResponse.setSuccess(success);
        serviceResponse.setList(surveyDtoList);

        return serviceResponse;

    }

    private ServiceResponse buildSurveyDtoPageserviceResponse(int statusCode, Boolean success, String message, Page<SurveyDto> surveyDtoPage){

        ServiceResponse serviceResponse = new ServiceResponse();

        serviceResponse.setStatusCode(statusCode);
        serviceResponse.setMessage(message);
        serviceResponse.setSuccess(success);
        serviceResponse.settPage(surveyDtoPage);

        return serviceResponse;

    }

    public Boolean validateSurveyCreationRequest(CreateSurveyRequest createSurveyRequest){

        if(createSurveyRequest == null){
            return false;
        }

        if(createSurveyRequest.getDescription() == null || createSurveyRequest.getName() == null){

            return false;
        }

        if(createSurveyRequest.getName().trim().isEmpty() || createSurveyRequest.getDescription().trim().isEmpty()){

            return false;

        }

        return true;

    }

    private List<SurveyDto> surveyDtoListFromSurveyList(List<Survey> surveyList){

        List<SurveyDto> surveyDtoList = new ArrayList<>();

        for(Survey survey : surveyList){

            surveyDtoList.add(modelMapper.map(survey, SurveyDto.class));
        }

        return surveyDtoList;
    }

    private Page<SurveyDto> convertSurveyEntityToSurveyDto(Page<Survey> surveyPage){

        List<Survey> surveyList = surveyPage.getContent();
        List<SurveyDto> surveyDtoList = new ArrayList<>();

        if( surveyList != null)
        {
            for(Survey survey : surveyList){
                SurveyDto surveyDto = modelMapper.map(survey, SurveyDto.class);
                surveyDtoList.add(surveyDto);
            }
        }

        int page = surveyPage.getNumber();
        int size = surveyPage.getSize();

        size = size <= 0 ? 10 : size;

        Pageable pageableSurvey = new QPageRequest(page, size);

        return new PageImpl<SurveyDto>(surveyDtoList, pageableSurvey, surveyPage.getTotalElements());

    }

    private List<QuestionDto> buildListOfQuestionsDto(Optional<Survey> survey){

        List<QuestionDto> questionDtoList = new ArrayList<>();

        if(survey.get().getQuestions() != null){

            Set<Question> questions = survey.get().getQuestions();

            for(Question question : questions){

                QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);

                List<AnswerDto> answerDtoList = new ArrayList<>();

                List<AnswerOptionsDto> answerOptionsDtoList = new ArrayList<>();

                Set<Answers> answers = question.getAnswers();

                if(answers != null){

                    for(Answers answerInSurvey : answers){

                        answerOptionsDtoList.add(modelMapper.map(answerInSurvey, AnswerOptionsDto.class));

                    }

                    questionDto.setAnswerOptionsDtoSet(answerOptionsDtoList);

                    questionDtoList.add(questionDto);

                }

            }

        }

        return questionDtoList;

    }

}

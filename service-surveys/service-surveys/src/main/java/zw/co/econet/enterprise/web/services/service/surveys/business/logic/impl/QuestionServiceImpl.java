package zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.common.utils.i18.api.MessageService;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.QuestionAuditableService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.QuestionService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyService;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Answers;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;
import zw.co.econet.enterprise.web.services.service.surveys.repository.AnswersRepository;
import zw.co.econet.enterprise.web.services.service.surveys.util.I18Code;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.QuestionDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionEditRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionAuditableService questionAuditableService;
    private final ModelMapper modelMapper;
    private final MessageService messageService;
    private final SurveyService surveyService;
    private final AnswersRepository answersRepository;
    private final ModelMapper mapper;

    public QuestionServiceImpl(QuestionAuditableService questionAuditableService, ModelMapper modelMapper,
                               MessageService messageService, SurveyService surveyService,
                               AnswersRepository answersRepository, ModelMapper mapper) {
        this.questionAuditableService = questionAuditableService;
        this.modelMapper = modelMapper;
        this.messageService = messageService;
        this.surveyService = surveyService;
        this.answersRepository = answersRepository;
        this.mapper = mapper;
    }

    @Override
    public ServiceResponse save(QuestionRequest questionRequest, Locale locale, String username) {

        ServiceResponse serviceResponse = new ServiceResponse();

        Question question = new Question();

        Long surveyId = Long.parseLong(questionRequest.getSurveyId());

        ServiceResponse<SurveyDto> surveySearched = surveyService.findById(surveyId,locale);

        question.setDescription(questionRequest.getDescription());
        question.setQuestionNumber(questionRequest.getQuestionNumber());
        question.setSurvey(modelMapper.map(surveySearched.getResult(), Survey.class));

        Question questionSaved = questionAuditableService.save(question,locale,username);

        QuestionDto questionDto = mapper.map(questionSaved,QuestionDto.class);

        if(questionRequest.getAnswerDtoSet()!= null)
        {
            Set<AnswerDto> answerDtoSet = saveAnswers(questionRequest.getAnswerDtoSet(),questionSaved);
            questionDto.setAnswerDtoSet(answerDtoSet);
        }

        serviceResponse.setSuccess(true);
        serviceResponse.setResult(questionDto);

        serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_CREATION_SUCCESSFUL.getCode(),
                new String[]{}, locale));

        return serviceResponse;
    }

    public Set<AnswerDto> saveAnswers(Set<AnswerDto> answerDtoSet,Question question)
    {
        Set<AnswerDto> answerDtoHashSet = new HashSet<AnswerDto>();

        for (AnswerDto answerDto:answerDtoSet) {

            Answers answerConverted = mapper.map(answerDto,Answers.class);

            answerConverted.setQuestion(question);
            Answers answerSaved = answersRepository.save(answerConverted);
            answerDtoHashSet.add(mapper.map(answerSaved,AnswerDto.class));
        }

        return answerDtoHashSet;
    }

    @Override
    public ServiceResponse edit(QuestionEditRequest questionRequest, Locale locale, String username) {

        ServiceResponse serviceResponse = new ServiceResponse();

        Optional<Question> questionOptional = questionAuditableService.findById(questionRequest.getId(),locale);

        Boolean isSuccess = questionOptional.isPresent();

        if(!isSuccess)
        {
            serviceResponse.setSuccess(false);

            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_NOT_FOUND.getCode(),
                    new String[]{}, locale));

            return serviceResponse;
        }

        RecordStatus recordStatus = RecordStatus.valueOf(questionRequest.getRecordStatus());

        Question question = mapper.map(questionRequest,Question.class);

        question.setDateCreated(questionOptional.get().getDateCreated());

        question.setSurvey(questionOptional.get().getSurvey());

        question.setRecordStatus(recordStatus);

        Question questionEdited = questionAuditableService.save(question,locale,username);

        serviceResponse.setSuccess(true);

        serviceResponse.setResult(mapper.map(questionEdited,QuestionDto.class));

        serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_EDITED_SUCCESSFUL.getCode(),
                new String[]{}, locale));


        return serviceResponse;
    }

    @Override
    public ServiceResponse findById(Long id, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        Optional<Question>  questionOptional = questionAuditableService.findById(id,locale);

        Boolean isSuccess = questionOptional.isPresent();

        if(!isSuccess)
        {
            serviceResponse.setSuccess(false);

            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_NOT_FOUND.getCode(),
                    new String[]{}, locale));

            return serviceResponse;
        }

        List<Answers> answersList =
                answersRepository.findAnswersByQuestionIdAndStatus(questionOptional.get().getId(),RecordStatus.ACTIVE);

        Set<Answers> answersSet = new HashSet<Answers>(answersList);

        Set<AnswerDto> answerDtoSet = convertAnswersSetToDto(answersSet);

        serviceResponse.setSuccess(true);

        QuestionDto questionDto = mapper.map(questionOptional.orElse(null),QuestionDto.class);

        questionDto.setAnswerDtoSet(answerDtoSet);

        serviceResponse.setResult(questionDto);

        serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_SEARCH_SUCCESS.getCode(),
                new String[]{}, locale));

        return serviceResponse;
    }

    @Override
    public ServiceResponse findAll(Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        List<Question> questionList = questionAuditableService.findAll(locale);

        Boolean isSuccess = questionList.isEmpty();

        if(isSuccess)
        {
            serviceResponse.setSuccess(false);

            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_NOT_FOUND.getCode(),
                    new String[]{}, locale));

            return serviceResponse;
        }

        serviceResponse.setSuccess(true);

        List<QuestionDto> questionDtoList = questionDtoListWithAnswersCreator(questionList);

        serviceResponse.setList(questionDtoList);

        serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_SEARCH_SUCCESS.getCode(),
                new String[]{}, locale));

        return serviceResponse;
    }

    @Override
    public ServiceResponse findByRecordStatus(RecordStatus recordStatus, Pageable pageable, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        List<Question> questionPage = questionAuditableService.findQuestionsByRecordStatus(recordStatus,pageable, locale);

        Boolean isSuccess = questionPage.isEmpty();

        if(isSuccess)
        {
            serviceResponse.setSuccess(false);

            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_NOT_FOUND.getCode(),
                    new String[]{}, locale));

            return serviceResponse;
        }

        serviceResponse.setSuccess(true);

        List<QuestionDto> questionDtoList = questionDtoListWithAnswersCreator(questionPage);

        serviceResponse.setList(questionDtoList);

        serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_SEARCH_SUCCESS.getCode(),
                new String[]{}, locale));

        return serviceResponse;
    }

    @Override
    public ServiceResponse findAll(Pageable pageable, Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        Page<Question> questionList = questionAuditableService.findAll(pageable,locale);

        Boolean isSuccess = questionList.isEmpty();

        if(isSuccess)
        {
            serviceResponse.setSuccess(false);

            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_NOT_FOUND.getCode(),
                    new String[]{}, locale));

            return serviceResponse;
        }

        serviceResponse.setSuccess(true);


        serviceResponse.settPage(questionList);

        serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_SEARCH_SUCCESS.getCode(),
                new String[]{}, locale));

        return serviceResponse;
    }

    @Override
    public ServiceResponse findQuestionsBySurvey(String surveyId,Locale locale) {

        ServiceResponse serviceResponse = new ServiceResponse();

        Long idSurvey = Long.parseLong(surveyId);

        ServiceResponse<SurveyDto> surveySearched = surveyService.findById(idSurvey,locale);

        Survey surveyConverted = mapper.map(surveySearched.getResult(),Survey.class);

        List<Question> questionList =
                questionAuditableService.findQuestionsBySurvey(surveyConverted);

        Boolean isEmpty = questionList.isEmpty();

        if(isEmpty)
        {
            serviceResponse.setSuccess(false);

            serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_NOT_FOUND.getCode(),
                    new String[]{}, locale));

            return serviceResponse;
        }

        serviceResponse.setSuccess(true);

        //TODO refactor
        serviceResponse.setList(questionDtoListWithAnswersCreator(questionList));

        serviceResponse.setMessage(messageService.getMessage(I18Code.MESSAGE_QUESTION_SEARCH_SUCCESS.getCode(),
                new String[]{}, locale));

        return serviceResponse;
    }

    public Set<AnswerDto> convertAnswersSetToDto(Set<Answers> answersSet)
    {
        Set<AnswerDto> answerDtoHashSet = new HashSet<AnswerDto>();

        for (Answers answers:answersSet) {

            answerDtoHashSet.add(mapper.map(answers,AnswerDto.class));
        }

        return answerDtoHashSet;
    }

    public List<QuestionDto> questionDtoListWithAnswersCreator(List<Question> questionList)
    {
        List<QuestionDto>  questionDtoList = new ArrayList<>();

        for(Question question:questionList)
        {
            QuestionDto questionDto = mapper.map(question,QuestionDto.class);
            questionDto.setAnswerDtoSet(convertAnswersSetToDto(question.getAnswers()));
            questionDtoList.add(questionDto);
        }

        return questionDtoList;
    }



}

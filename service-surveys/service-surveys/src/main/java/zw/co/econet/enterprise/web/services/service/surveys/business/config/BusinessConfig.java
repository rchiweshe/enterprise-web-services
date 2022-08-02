package zw.co.econet.enterprise.web.services.service.surveys.business.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import zw.co.econet.commons.msisdn.parser.MsisdnParser;
import zw.co.econet.enterprise.web.services.common.utils.config.UtilsConfig;
import zw.co.econet.enterprise.web.services.common.utils.i18.api.MessageService;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.QuestionAuditableService;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.SurveyResponseServiceAuditable;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.SurveyServiceAuditable;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.impl.QuestionAuditableServiceImpl;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.impl.SurveyResponseServiceAuditableImpl;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.impl.SurveyServiceAuditableImpl;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.AnswerService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.QuestionService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyResponseService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl.AnswerServiceImpl;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl.QuestionServiceImpl;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl.SurveyResponseServiceImpl;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl.SurveyServiceImpl;
import zw.co.econet.enterprise.web.services.service.surveys.repository.AnswersRepository;
import zw.co.econet.enterprise.web.services.service.surveys.repository.QuestionRepository;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyRepository;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyResponseRepository;
import zw.co.econet.enterprise.web.services.service.surveys.repository.config.DataConfig;

@Configuration
@Import({DataConfig.class, UtilsConfig.class})
public class BusinessConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    QuestionAuditableService questionAuditableService(QuestionRepository questionRepository)
    {
        return new QuestionAuditableServiceImpl(questionRepository);
    }

    @Bean
    QuestionService questionService(QuestionAuditableService questionAuditableService, ModelMapper modelMapper,
                                    MessageService messageService, SurveyService surveyService,
                                    AnswersRepository answersRepository, ModelMapper mapper)
    {
        return new QuestionServiceImpl(questionAuditableService, modelMapper, messageService,
                surveyService, answersRepository, mapper);
    }

    @Bean
    AnswerService answerService(AnswersRepository answersRepository, QuestionRepository questionRepository,
                                ModelMapper mapper) {
        return new AnswerServiceImpl(answersRepository, questionRepository, mapper);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SurveyServiceAuditable surveyServiceAuditable(SurveyRepository surveyRepository){

        return new SurveyServiceAuditableImpl(surveyRepository);

    }

    @Bean
    public SurveyService surveyService(SurveyServiceAuditable surveyServiceAuditable,
                                       ModelMapper modelMapper,
                                       MessageService messageService,
                                       SurveyRepository surveyRepository,
                                       ModelMapper mapper){
        return new SurveyServiceImpl(surveyServiceAuditable, modelMapper, messageService, surveyRepository, mapper);
    }

    @Bean
    public SurveyResponseServiceAuditable surveyResponseServiceAuditable(SurveyResponseRepository surveyResponseRepository){
        return new SurveyResponseServiceAuditableImpl(surveyResponseRepository);
    }

    @Bean
    public SurveyResponseService surveyResponseService(ModelMapper modelMapper,
                                                       SurveyResponseServiceAuditable surveyResponseServiceAuditable,
                                                       MessageService messageService,
                                                       QuestionRepository questionRepository,
                                                       SurveyServiceAuditable surveyServiceAuditable,
                                                       MsisdnParser msisdnParser){

        return new SurveyResponseServiceImpl(modelMapper, surveyResponseServiceAuditable, messageService,
                questionRepository, surveyServiceAuditable, msisdnParser);
    }
}

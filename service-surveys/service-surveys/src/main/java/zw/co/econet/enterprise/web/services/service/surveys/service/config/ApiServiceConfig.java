package zw.co.econet.enterprise.web.services.service.surveys.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import zw.co.econet.enterprise.web.services.service.surveys.business.config.BusinessConfig;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.QuestionService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyResponseService;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.SurveyService;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.QuestionProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.SurveyProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.SurveyResponseProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.impl.QuestionProcessorImpl;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.impl.SurveyProcessorImpl;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.impl.SurveyResponseProcessorImpl;

@Configuration
@Import({BusinessConfig.class})
public class ApiServiceConfig {

    @Bean
    public SurveyProcessor surveyProcessor(SurveyService surveyService){

        return new SurveyProcessorImpl(surveyService);

    }

    @Bean
    public SurveyResponseProcessor surveyResponseProcessor(SurveyResponseService surveyResponseService){
        return new SurveyResponseProcessorImpl(surveyResponseService);
    }

    @Bean
    QuestionProcessor questionProcessor(QuestionService questionService)
    {
        return  new QuestionProcessorImpl(questionService);
    }
}

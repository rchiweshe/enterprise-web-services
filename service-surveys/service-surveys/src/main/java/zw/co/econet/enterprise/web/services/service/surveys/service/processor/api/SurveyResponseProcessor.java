package zw.co.econet.enterprise.web.services.service.surveys.service.processor.api;

import java.util.List;
import java.util.Locale;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyResponseDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveysResponse;

public interface SurveyResponseProcessor {

    SurveysResponse save(SurveyResponseDto surveyResponseDto, Locale locale);

    SurveysResponse saveAll(List<SurveyResponseDto> surveyResponseDto, Locale locale);

    SurveysResponse findById(Long id, Locale locale);

    SurveysResponse findBySuveryId(String id, Locale locale);

    SurveysResponse findByQuestion(Long questionId, Locale locale);

    SurveysResponse findByMsisdn(String msisdn,  Locale locale);

    SurveysResponse findAll(int page, int size, Locale locale);

    SurveysResponse findByMsisdnAndSuveryId(String msisdn, String surveyId, Locale locale);

    SurveysResponse findByMsisdnAndQuestion(String msisdn, String questionId, Locale locale);

}

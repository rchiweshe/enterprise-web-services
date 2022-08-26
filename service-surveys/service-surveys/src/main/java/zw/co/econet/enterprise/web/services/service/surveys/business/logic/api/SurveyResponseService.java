package zw.co.econet.enterprise.web.services.service.surveys.business.logic.api;

import java.util.List;
import java.util.Locale;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyResponseDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;

public interface SurveyResponseService {

    ServiceResponse save(SurveyResponseDto surveyResponseDto, Locale locale);

    ServiceResponse saveAll(List<SurveyResponseDto> surveyResponseDto, Locale locale);

    ServiceResponse findById(Long id, Locale locale);

    ServiceResponse findBySuveryId(String id, Locale locale);

    ServiceResponse findByQuestion(Long questionId, Locale locale);

    ServiceResponse findByMsisdn(String msisdn, Locale locale);

    ServiceResponse findAll(int page, int size, Locale locale);

    ServiceResponse findByMsisdnAndSuveryId(String msisdn, String surveyId, Locale locale);

    ServiceResponse findByMsisdnAndQuestion(String msisdn, String questionId, Locale locale);

}

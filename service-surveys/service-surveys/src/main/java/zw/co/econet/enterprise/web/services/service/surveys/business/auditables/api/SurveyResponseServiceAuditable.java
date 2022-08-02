package zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyResponse;

public interface SurveyResponseServiceAuditable {

    SurveyResponse save(SurveyResponse surveyResponse, Locale locale);

    List<SurveyResponse> saveAll(List<SurveyResponse> surveyResponses, Locale locale);

    Optional<SurveyResponse> findById(Long id, Locale locale);

    List<SurveyResponse> findBySuveryId(String id, Locale locale);

    List<SurveyResponse> findByQuestion(Question question, Locale locale);

    List<SurveyResponse> findByMsisdn(String msisdn, Locale locale);

    Page<SurveyResponse> findAll(Pageable pageable, Locale locale);

    List<SurveyResponse> findByMsisdnAndSuveryId(String msisdn, String surveyId, Locale locale);

    List<SurveyResponse> findByMsisdnAndQuestion(String msisdn, Question question, Locale locale);

    List<SurveyResponse> findByMsisdnAndQuestionAndSuveryId(String msisdn, Question question, String surveyId);

}

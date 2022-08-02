package zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;

public interface SurveyServiceAuditable {

    public Survey save(Survey survey, Locale locale, String username);

    public Optional<Survey> findById(Long id, Locale locale);

    public List<Survey> findAll(Locale locale);

    public Page<Survey> findBySurveyStatus(SurveyStatus surveyStatus, Pageable pageable, Locale locale);

    public Page<Survey> findAll(Pageable pageable, Locale locale);

    public List<Survey> findSurveysWithMatchingName(String name, Locale locale);

    public Optional<Survey> findBySurveyName(String name, Locale locale);

}

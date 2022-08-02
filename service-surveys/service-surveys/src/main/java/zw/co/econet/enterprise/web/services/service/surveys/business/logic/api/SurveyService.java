package zw.co.econet.enterprise.web.services.service.surveys.business.logic.api;

import java.util.Locale;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;

public interface SurveyService {

    public ServiceResponse save(SurveyDto surveyDto, Locale locale, String username);

    public ServiceResponse edit(SurveyDto surveyDto, Locale locale,String username);

    public ServiceResponse findById(Long id, Locale locale);

    public ServiceResponse findAll(Locale locale);

    public ServiceResponse findBySurveyStatus(SurveyStatus surveyStatus, Pageable pageable, Locale locale);

    public ServiceResponse findAll(Pageable pageable, Locale locale);

    public ServiceResponse findSurveysWithMatchingName(String name, Locale locale);

    public ServiceResponse findBySurveyName(String name, Locale locale);

}

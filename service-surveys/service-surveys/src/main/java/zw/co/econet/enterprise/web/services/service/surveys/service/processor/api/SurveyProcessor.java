package zw.co.econet.enterprise.web.services.service.surveys.service.processor.api;

import java.util.Locale;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.CreateSurveyRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveyResponse;

public interface SurveyProcessor {

    public SurveyResponse save(CreateSurveyRequest createSurveyRequest, Locale locale, String username);

    public SurveyResponse edit(SurveyDto surveyDto, Locale locale,String username);

    public SurveyResponse findById(Long id, Locale locale);

    public SurveyResponse findAll(Locale locale);

    public SurveyResponse findBySurveyStatus(SurveyStatus surveyStatus, int page, int size, Locale locale);

    public SurveyResponse findAll(int page, int size, Locale locale);

    public SurveyResponse findSurveysWithMatchingName(String name, Locale locale);

    public SurveyResponse findBySurveyName(String name, Locale locale);

}

package zw.co.econet.enterprise.web.services.service.surveys.business.auditables.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.SurveyServiceAuditable;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyRepository;

public class SurveyServiceAuditableImpl implements SurveyServiceAuditable {

    private SurveyRepository surveyRepository;

    public SurveyServiceAuditableImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Survey save(Survey survey, Locale locale, String username) {
        return surveyRepository.save(survey);
    }

    @Override
    public Optional<Survey> findById(Long id, Locale locale) {
        return surveyRepository.findById(id);
    }

    @Override
    public List<Survey> findAll(Locale locale) {
        return surveyRepository.findAll();
    }

    @Override
    public Page<Survey> findBySurveyStatus(SurveyStatus surveyStatus, Pageable pageable, Locale locale) {
        return surveyRepository.findBySurveyStatus(surveyStatus, pageable);
    }

    @Override
    public Page<Survey> findAll(Pageable pageable, Locale locale) {
        return surveyRepository.findAll(pageable);
    }

   @Override
    public List<Survey> findSurveysWithMatchingName(String name, Locale locale) {
        return surveyRepository.findSurveysWithMatchingName(name);
    }

    @Override
    public Optional<Survey> findBySurveyName(String name, Locale locale) {
        return surveyRepository.findByName(name);
    }
}

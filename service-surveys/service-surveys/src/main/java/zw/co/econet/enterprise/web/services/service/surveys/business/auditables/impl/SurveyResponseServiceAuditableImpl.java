package zw.co.econet.enterprise.web.services.service.surveys.business.auditables.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.SurveyResponseServiceAuditable;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyResponse;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyResponseRepository;

public class SurveyResponseServiceAuditableImpl implements SurveyResponseServiceAuditable {

    private final SurveyResponseRepository surveyResponseRepository;

    public SurveyResponseServiceAuditableImpl(SurveyResponseRepository surveyResponseRepository) {
        this.surveyResponseRepository = surveyResponseRepository;
    }

    @Override
    public SurveyResponse save(SurveyResponse surveyResponse, Locale locale) {
        return surveyResponseRepository.save(surveyResponse);
    }

    @Override
    public List<SurveyResponse> saveAll(List<SurveyResponse> surveyResponses, Locale locale) {
        return surveyResponseRepository.saveAll(surveyResponses);
    }

    @Override
    public Optional<SurveyResponse> findById(Long id, Locale locale) {
        return surveyResponseRepository.findById(id);
    }

    @Override
    public List<SurveyResponse> findBySuveryId(String surveyId, Locale locale) {
        return surveyResponseRepository.findBySuveryId(surveyId);
    }

    @Override
    public List<SurveyResponse> findByQuestion(Question question, Locale locale) {
        return surveyResponseRepository.findByQuestion(question);
    }

    @Override
    public List<SurveyResponse> findByMsisdn(String msisdn, Locale locale) {
        return surveyResponseRepository.findByMsisdn(msisdn);
    }

    @Override
    public Page<SurveyResponse> findAll(Pageable pageable, Locale locale) {
        return surveyResponseRepository.findAll(pageable);
    }

    @Override
    public List<SurveyResponse> findByMsisdnAndSuveryId(String msisdn, String surveyId, Locale locale) {
        return surveyResponseRepository.findByMsisdnAndSuveryId(msisdn, surveyId);
    }

    @Override
    public List<SurveyResponse> findByMsisdnAndQuestion(String msisdn, Question question, Locale locale) {
        return surveyResponseRepository.findByMsisdnAndQuestion(msisdn, question);
    }

    @Override
    public List<SurveyResponse> findByMsisdnAndQuestionAndSuveryId(String msisdn, Question question, String surveyId) {
        return surveyResponseRepository.findByMsisdnAndQuestionAndSuveryId(msisdn, question, surveyId);
    }
}

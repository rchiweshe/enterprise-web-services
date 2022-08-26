package zw.co.econet.enterprise.web.services.service.surveys.business.auditables.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api.QuestionAuditableService;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;
import zw.co.econet.enterprise.web.services.service.surveys.repository.QuestionRepository;

public class QuestionAuditableServiceImpl implements QuestionAuditableService {

    private QuestionRepository questionRepository;

    public QuestionAuditableServiceImpl(QuestionRepository questionRepository) {

        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question, Locale locale, String username) {

        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(Long id, Locale locale) {

        return questionRepository.findById(id);
    }

    @Override
    public List<Question> findAll(Locale locale) {

        return questionRepository.findAll();
    }

    @Override
    public List<Question> findQuestionsByRecordStatus(RecordStatus recordStatus, Pageable pageable, Locale locale) {

        return questionRepository.findQuestionsByRecordStatus(recordStatus);
    }

    @Override
    public Page<Question> findAll(Pageable pageable, Locale locale) {

        return questionRepository.findAll(pageable);
    }

    @Override
    public List<Question> findQuestionsBySurvey(Survey survey) {

        return questionRepository.findQuestionsBySurvey(survey);
    }
}

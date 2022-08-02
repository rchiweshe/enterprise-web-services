package zw.co.econet.enterprise.web.services.service.surveys.business.auditables.api;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;

public interface QuestionAuditableService {

     Question save(Question question, Locale locale, String username);

     Optional<Question> findById(Long id, Locale locale);

     List<Question> findAll(Locale locale);

     List<Question> findQuestionsByRecordStatus(RecordStatus recordStatus, Pageable pageable, Locale locale);

     Page<Question> findAll(Pageable pageable, Locale locale);

     List<Question> findQuestionsBySurvey(Survey survey);
}

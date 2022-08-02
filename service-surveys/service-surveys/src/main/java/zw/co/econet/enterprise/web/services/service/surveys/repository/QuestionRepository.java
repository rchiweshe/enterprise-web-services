package zw.co.econet.enterprise.web.services.service.surveys.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    Question save(Question survey);

    Optional<Question> findById(Long id);

    List<Question> findAll();

    List<Question> findQuestionsBySurvey(Survey survey);

    List<Question> findQuestionsByRecordStatus(RecordStatus recordStatus);

    Page<Question> findAll(Pageable pageable);

}

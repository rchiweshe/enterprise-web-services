package zw.co.econet.enterprise.web.services.service.surveys.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long>, JpaSpecificationExecutor<Answers> {
    List<Answers> findAnswersByQuestionIdAndStatus(Long questionId, RecordStatus status);
}

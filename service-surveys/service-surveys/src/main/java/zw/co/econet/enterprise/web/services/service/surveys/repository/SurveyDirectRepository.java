package zw.co.econet.enterprise.web.services.service.surveys.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyDirect;

public interface SurveyDirectRepository extends JpaRepository<SurveyDirect, Long>,
        JpaSpecificationExecutor<SurveyDirect> {
    SurveyDirect save(SurveyDirect surveyDirect);
    Optional<SurveyDirect> findByMsisdnAndAndSurveyNameAndQuestion(String msisdn, String surveyNName, String question);
}

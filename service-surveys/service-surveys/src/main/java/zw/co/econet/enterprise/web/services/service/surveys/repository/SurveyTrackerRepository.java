package zw.co.econet.enterprise.web.services.service.surveys.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyTracker;

public interface SurveyTrackerRepository extends JpaRepository<SurveyTracker, Long>, JpaSpecificationExecutor<SurveyTracker> {

    Optional<SurveyTracker> findByMsisdnAndSurveyName(String msisdn, String surveyName);

}

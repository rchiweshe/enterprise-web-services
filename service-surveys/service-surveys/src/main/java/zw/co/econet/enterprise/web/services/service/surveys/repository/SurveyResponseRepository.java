package zw.co.econet.enterprise.web.services.service.surveys.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyResponse;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long>, JpaSpecificationExecutor<SurveyResponse> {

    SurveyResponse save(SurveyResponse surveyResponse);

    Optional<SurveyResponse> findById(Long id);

    List<SurveyResponse> findBySuveryId(String id);

    List<SurveyResponse> findByQuestion(Question question);

    List<SurveyResponse> findByMsisdnAndSuveryId(String msisdn, String surveyId);

    List<SurveyResponse> findByMsisdnAndQuestion(String msisdn, Question question);

    List<SurveyResponse> findByMsisdn(String msisdn);

    Page<SurveyResponse> findAll(Pageable pageable);

    List<SurveyResponse> findByMsisdnAndQuestionAndSuveryId(String msisdn, Question question, String surveyId);

}


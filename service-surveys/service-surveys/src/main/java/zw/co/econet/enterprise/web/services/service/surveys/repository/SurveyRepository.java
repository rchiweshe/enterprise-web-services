package zw.co.econet.enterprise.web.services.service.surveys.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey>{

    public Survey save(Survey survey);

    public Optional<Survey> findByName(String name);

    public Optional<Survey> findById(Long id);

    public List<Survey> findAll();

    public Page<Survey> findBySurveyStatus(SurveyStatus surveyStatus, Pageable pageable);

    public Page<Survey> findAll(Pageable pageable);

    @Query(value = "SELECT *  FROM survey s WHERE s.name like %:name%", nativeQuery = true)
    public List<Survey> findSurveysWithMatchingName(@Param("name") String name);

}

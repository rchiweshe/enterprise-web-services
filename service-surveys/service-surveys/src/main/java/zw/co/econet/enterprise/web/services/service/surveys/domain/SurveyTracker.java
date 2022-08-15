package zw.co.econet.enterprise.web.services.service.surveys.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "survey_tracker",
       indexes = {
               @Index(name = "survey_tracker_msisdn_index", columnList = "msisdn"),
               @Index(name = "survey_tracker_name_index", columnList = "surveyName"),
       })
public class SurveyTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String msisdn;

    private String surveyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    @Override
    public String toString() {
        return "SurveyTracker{" + "id=" + id + ", msisdn='" + msisdn + '\'' + ", surveyName='" + surveyName + '\'' + '}';
    }
}

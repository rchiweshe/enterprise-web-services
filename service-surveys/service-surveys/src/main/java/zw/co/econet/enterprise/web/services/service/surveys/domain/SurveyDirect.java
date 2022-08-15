package zw.co.econet.enterprise.web.services.service.surveys.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;

@Entity
@Table(name = "direct_survey",
       indexes = {
               @Index(name = "msisdn_index", columnList = "msisdn"),
               @Index(name = "response_index", columnList = "response"),
               @Index(name = "question_index", columnList = "question"),
               @Index(name = "surveyName_index", columnList = "surveyName"),
               @Index(name = "uniqueIndex", columnList = "question")
       })
public class SurveyDirect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String msisdn;
    private String response;
    private String surveyName;
    private String question;
    private RecordStatus status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastModified;

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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(LocalDateTime dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @PrePersist
    private void prePersist() {
        this.dateCreated = LocalDateTime.now();
        this.dateLastModified = LocalDateTime.now();
        this.status = RecordStatus.ACTIVE;
    }

    @Override
    public String toString() {
        return "SurveyDirect{" + "id=" + id + ", msisdn='" + msisdn + '\'' + ", response='" + response + '\'' + ", surveyName='" + surveyName + '\'' + ", question='" + question + '\'' + ", status=" + status + ", dateCreated=" + dateCreated + ", dateLastModified=" + dateLastModified + '}';
    }
}

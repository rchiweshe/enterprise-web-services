package zw.co.econet.enterprise.web.services.service.surveys.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;

@Entity
@Table(name = "suvery_response")
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String msisdn;

    private String answer;

    private String suveryId;

    @Enumerated(value = EnumType.STRING)
    private RecordStatus recordStatus;

    private LocalDateTime dateCreated;

    private LocalDateTime dateLastModified;

    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getSuveryId() {
        return suveryId;
    }

    public void setSuveryId(String suveryId) {
        this.suveryId = suveryId;
    }

    @PreUpdate
    public void update(){
        dateLastModified = LocalDateTime.now();
    }

    @PrePersist
    public void create(){
        dateCreated = LocalDateTime.now();
        recordStatus = RecordStatus.ACTIVE;
    }

    @Override
    public String toString() {
        return "SurveyResponse{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", answer='" + answer + '\'' +
                ", suveryId='" + suveryId + '\'' +
                ", recordStatus=" + recordStatus +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", question=" + question +
                '}';
    }
}

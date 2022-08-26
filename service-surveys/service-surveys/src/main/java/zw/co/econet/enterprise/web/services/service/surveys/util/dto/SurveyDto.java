package zw.co.econet.enterprise.web.services.service.surveys.util.dto;

import java.time.LocalDateTime;
import java.util.List;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;

public class SurveyDto {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime dateCreated;

    private LocalDateTime dateLastModified;

    private SurveyStatus surveyStatus;

    private RecordStatus recordStatus;

    private List<QuestionDto> questionDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public SurveyStatus getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(SurveyStatus surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public List<QuestionDto> getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(List<QuestionDto> questionDto) {
        this.questionDto = questionDto;
    }

    @Override
    public String toString() {
        return "SurveyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", surveyStatus=" + surveyStatus +
                ", recordStatus=" + recordStatus +
                '}';
    }
}

package zw.co.econet.enterprise.web.services.service.surveys.util.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;

public class AnswerOptionsDto {
    private Long id;
    private String description;
    private String status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastModified;
    private QuestionDto questionDto;
    private Long answerNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Long getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Long answerNumber) {
        this.answerNumber = answerNumber;
    }

    @Override
    public String toString() {
        return "AnswerOptionsDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", questionDto=" + questionDto +
                ", answerNumber=" + answerNumber +
                '}';
    }

    public static class QuestionDto {

        private Long   id;
        private LocalDateTime dateCreated;
        private LocalDateTime dateModified;
        private RecordStatus recordStatus;
        private String description;
        private String questionNumber;
        private String surveyId;
        private List<AnswerOptionsDto> answerOptionsDtoSet;
        private Set<AnswerDto> answerDtoSet;

        public Set<AnswerDto> getAnswerDtoSet() {
            return answerDtoSet;
        }

        public void setAnswerDtoSet(Set<AnswerDto> answerDtoSet) {
            this.answerDtoSet = answerDtoSet;
        }

        public String getSurveyId() {
            return surveyId;
        }

        public void setSurveyId(String surveyId) {
            this.surveyId = surveyId;
        }

        public String getQuestionNumber() {
            return questionNumber;
        }

        public void setQuestionNumber(String questionNumber) {
            this.questionNumber = questionNumber;
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

        public LocalDateTime getDateModified() {
            return dateModified;
        }

        public void setDateModified(LocalDateTime dateModified) {
            this.dateModified = dateModified;
        }

        public RecordStatus getRecordStatus() {
            return recordStatus;
        }

        public void setRecordStatus(RecordStatus recordStatus) {
            this.recordStatus = recordStatus;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public List<AnswerOptionsDto> getAnswerOptionsDtoSet() {
            return answerOptionsDtoSet;
        }

        public void setAnswerOptionsDtoSet(List<AnswerOptionsDto> answerOptionsDtoSet) {
            this.answerOptionsDtoSet = answerOptionsDtoSet;
        }

        @Override
        public String toString() {
            return "QuestionDto{" +
                    "id=" + id +
                    ", dateCreated=" + dateCreated +
                    ", dateModified=" + dateModified +
                    ", recordStatus=" + recordStatus +
                    '}';
        }
    }
}
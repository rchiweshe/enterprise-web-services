package zw.co.econet.enterprise.web.services.service.surveys.util.request;

public class QuestionEditRequest {

    private String description;

    private String questionNumber;

    private String surveyId;

    private String recordStatus;

    private Long id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionEditRequest{" +
                "description='" + description + '\'' +
                ", questionNumber='" + questionNumber + '\'' +
                ", surveyId='" + surveyId + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", id=" + id +
                '}';
    }
}

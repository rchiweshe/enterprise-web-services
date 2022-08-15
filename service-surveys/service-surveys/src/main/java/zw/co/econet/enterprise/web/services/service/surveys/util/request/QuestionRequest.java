package zw.co.econet.enterprise.web.services.service.surveys.util.request;

import java.util.Set;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerDto;

public class QuestionRequest {

    private String description;

    private String questionNumber;

    private String surveyId;

    private Set<CreateAnswerRequest> createAnswerRequestSet;

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

    public Set<CreateAnswerRequest> getCreateAnswerRequestSet() {
        return createAnswerRequestSet;
    }

    public void setCreateAnswerRequestSet(Set<CreateAnswerRequest> createAnswerRequestSet) {
        this.createAnswerRequestSet = createAnswerRequestSet;
    }

    @Override
    public String toString() {
        return "QuestionRequest{" + "description='" + description + '\'' +
                ", questionNumber='" + questionNumber + '\'' + ", surveyId='" + surveyId +
                '\'' + ", createAnswerRequestSet=" + createAnswerRequestSet + '}';
    }
}

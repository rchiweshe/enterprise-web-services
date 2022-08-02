package zw.co.econet.enterprise.web.services.service.surveys.util.request;

import java.util.Set;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerDto;

public class QuestionRequest {

    private String description;

    private String questionNumber;

    private String surveyId;

    private Set<AnswerDto> answerDtoSet;

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

    public Set<AnswerDto> getAnswerDtoSet() {
        return answerDtoSet;
    }

    public void setAnswerDtoSet(Set<AnswerDto> answerDtoSet) {
        this.answerDtoSet = answerDtoSet;
    }

    @Override
    public String toString() {
        return "QuestionRequest{" +
                "description='" + description + '\'' +
                ", questionNumber='" + questionNumber + '\'' +
                ", surveyId='" + surveyId + '\'' +
                ", answerDtoSet=" + answerDtoSet +
                '}';
    }
}

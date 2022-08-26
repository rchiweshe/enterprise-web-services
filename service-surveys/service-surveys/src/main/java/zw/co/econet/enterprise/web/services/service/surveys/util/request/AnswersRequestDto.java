package zw.co.econet.enterprise.web.services.service.surveys.util.request;

public class AnswersRequestDto {
    private String description;
    private Long question;
    private Long answerNumber;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }

    public Long getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Long answerNumber) {
        this.answerNumber = answerNumber;
    }

    @Override
    public String toString() {
        return "AnswersRequestDto{" +
                "description='" + description + '\'' +
                ", question=" + question +
                ", answerNumber=" + answerNumber +
                '}';
    }
}

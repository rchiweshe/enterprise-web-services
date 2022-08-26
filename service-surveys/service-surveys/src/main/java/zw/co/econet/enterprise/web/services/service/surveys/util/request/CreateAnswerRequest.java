package zw.co.econet.enterprise.web.services.service.surveys.util.request;

public class CreateAnswerRequest {

    private String description;
    private Long answerNumber;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Long answerNumber) {
        this.answerNumber = answerNumber;
    }

    @Override
    public String toString() {
        return "CreateAnswerRrequest{" + "description='" + description + '\'' + ", answerNumber=" + answerNumber + '}';
    }
}

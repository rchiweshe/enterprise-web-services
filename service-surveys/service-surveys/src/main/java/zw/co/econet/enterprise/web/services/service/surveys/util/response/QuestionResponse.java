package zw.co.econet.enterprise.web.services.service.surveys.util.response;

import java.util.List;
import org.springframework.data.domain.Page;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.QuestionDto;

public class QuestionResponse {

    private int statusCode;
    private boolean success;
    private String message;
    private QuestionDto questionDto;
    private List<QuestionDto> questionDtoList;
    private List<Question> questionList;
    private Page<QuestionDto> questionDtoPage;
    private Page<Question> questionPage;

    public Page<Question> getQuestionPage() {
        return questionPage;
    }

    public void setQuestionPage(Page<Question> questionPage) {
        this.questionPage = questionPage;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public QuestionDto getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }

    public List<QuestionDto> getQuestionDtoList() {
        return questionDtoList;
    }

    public void setQuestionDtoList(List<QuestionDto> questionDtoList) {
        this.questionDtoList = questionDtoList;
    }

    public Page<QuestionDto> getQuestionDtoPage() {
        return questionDtoPage;
    }

    public void setQuestionDtoPage(Page<QuestionDto> questionDtoPage) {
        this.questionDtoPage = questionDtoPage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "QuestionResponse{" + "statusCode=" + statusCode + ", success=" + success +
                ", message='" + message + '\'' + ", questionDto=" + questionDto +
                ", questionDtoList=" + questionDtoList + ", questionList=" + questionList +
                ", questionDtoPage=" + questionDtoPage + ", questionPage=" + questionPage + '}';
    }
}

package zw.co.econet.enterprise.web.services.service.surveys.util.response;

import java.util.List;
import org.springframework.data.domain.Page;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDto;

public class SurveyResponse {

    private int statusCode;
    private boolean success;
    private String message;
    private SurveyDto surveyDto;
    private List<SurveyDto> surveyDtoList;
    private Page<SurveyDto> surveyDtoPage;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SurveyDto getSurveyDto() {
        return surveyDto;
    }

    public void setSurveyDto(SurveyDto surveyDto) {
        this.surveyDto = surveyDto;
    }

    public List<SurveyDto> getSurveyDtoList() {
        return surveyDtoList;
    }

    public void setSurveyDtoList(List<SurveyDto> surveyDtoList) {
        this.surveyDtoList = surveyDtoList;
    }

    public Page<SurveyDto> getSurveyDtoPage() {
        return surveyDtoPage;
    }

    public void setSurveyDtoPage(Page<SurveyDto> surveyDtoPage) {
        this.surveyDtoPage = surveyDtoPage;
    }

    @Override
    public String toString() {
        return "SurveyResponse{" + "statusCode=" + statusCode + ", success=" + success + ", message='" + message + '\'' + ", surveyDto=" + surveyDto + ", surveyDtoList=" + surveyDtoList + ", surveyDtoPage=" + surveyDtoPage + '}';
    }
}

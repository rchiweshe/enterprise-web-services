package zw.co.econet.enterprise.web.services.service.surveys.util.response;

import java.util.List;
import org.springframework.data.domain.Page;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyResponseDto;

public class SurveysResponse {

    private int statusCode;
    private boolean success;
    private String message;
    private SurveyResponseDto surveyResponseDto;
    private List<SurveyResponseDto> surveyResponseDtos;
    private Page<SurveyResponseDto> surveyResponseDtoPage;

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

    public SurveyResponseDto getSurveyResponseDto() {
        return surveyResponseDto;
    }

    public void setSurveyResponseDto(SurveyResponseDto surveyResponseDto) {
        this.surveyResponseDto = surveyResponseDto;
    }

    public List<SurveyResponseDto> getSurveyResponseDtos() {
        return surveyResponseDtos;
    }

    public void setSurveyResponseDtos(List<SurveyResponseDto> surveyResponseDtos) {
        this.surveyResponseDtos = surveyResponseDtos;
    }

    public Page<SurveyResponseDto> getSurveyResponseDtoPage() {
        return surveyResponseDtoPage;
    }

    public void setSurveyResponseDtoPage(Page<SurveyResponseDto> surveyResponseDtoPage) {
        this.surveyResponseDtoPage = surveyResponseDtoPage;
    }

    @Override
    public String toString() {
        return "SurveysResponse{" +
                "statusCode=" + statusCode +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", surveyResponseDto=" + surveyResponseDto +
                ", surveyResponseDtos=" + surveyResponseDtos +
                ", surveyResponseDtoPage=" + surveyResponseDtoPage +
                '}';
    }
}

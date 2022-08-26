package zw.co.econet.enterprise.web.services.service.surveys.util.dto;

public class SurveyDirectDto {

    private String surveyName;
    private String question;
    private String response;
    private String msisdn;
    private String complete;

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "SurveyDirectDto{" + "surveyName='" + surveyName + '\'' + ", question='" + question + '\'' + ", response='" + response + '\'' + ", msisdn='" + msisdn + '\'' + ", complete=" + complete + '}';
    }
}

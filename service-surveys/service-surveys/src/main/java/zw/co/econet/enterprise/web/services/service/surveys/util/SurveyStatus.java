package zw.co.econet.enterprise.web.services.service.surveys.util;

public enum SurveyStatus {

    SENT("SENT"), DRAFT("DRAFT"),PENDING_APPROVAL("PENDING_APPROVAL");

    SurveyStatus(String suveryStatus) {
        this.suveryStatus = suveryStatus;
    }

    private String suveryStatus;

    public String getSuveryStatus() {
        return suveryStatus;
    }

}

package zw.co.econet.enterprise.web.services.service.surveys.util.wrapper;

public class MsisdnWrapper {

    private boolean msisdnIsValid;

    private String msisdn;

    private String message;

    public boolean isMsisdnIsValid() {
        return msisdnIsValid;
    }

    public void setMsisdnIsValid(boolean msisdnIsValid) {
        this.msisdnIsValid = msisdnIsValid;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MsisdnWrapper{" +
                "msisdnIsValid=" + msisdnIsValid +
                ", msisdn='" + msisdn + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

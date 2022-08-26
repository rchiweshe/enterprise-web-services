package zw.co.econet.servicewhitelist.utils.wrappers;

public class WhitelistWrapper {

    private boolean success;

    private String msisdn;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public String toString() {
        return "WhitelistWrapper{" +
                "success=" + success +
                ", msisdn='" + msisdn + '\'' +
                '}';
    }
}

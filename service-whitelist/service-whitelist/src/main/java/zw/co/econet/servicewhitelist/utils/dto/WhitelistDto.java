package zw.co.econet.servicewhitelist.utils.dto;

public class WhitelistDto {

    public Long id;

    public String msisdn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public String toString() {
        return "WhitelistDto{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                '}';
    }
}

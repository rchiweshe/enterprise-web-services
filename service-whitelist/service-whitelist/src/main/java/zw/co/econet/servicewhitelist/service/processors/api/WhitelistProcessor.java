package zw.co.econet.servicewhitelist.service.processors.api;

import java.util.Locale;
import zw.co.econet.servicewhitelist.utils.response.WhiteListResponse;

public interface WhitelistProcessor {

    public WhiteListResponse whitelistsNumber(String msisd, Locale locale);

    public WhiteListResponse findByMsisdn(String msisdn, Locale locale);

    public  WhiteListResponse findAll(Locale locale);

}

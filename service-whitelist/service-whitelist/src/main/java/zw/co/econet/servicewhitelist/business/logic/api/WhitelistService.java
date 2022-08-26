package zw.co.econet.servicewhitelist.business.logic.api;

import java.util.Locale;
import zw.co.econet.servicewhitelist.utils.response.ServiceResponse;

public interface WhitelistService {

    public ServiceResponse findByMsisdn(String msisdn, Locale locale);

    public ServiceResponse findAll(Locale locale);

    public ServiceResponse saveMsisdn(String msisdn, Locale locale);

}

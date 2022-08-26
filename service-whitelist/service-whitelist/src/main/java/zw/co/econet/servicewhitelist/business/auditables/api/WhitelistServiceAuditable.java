package zw.co.econet.servicewhitelist.business.auditables.api;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import zw.co.econet.servicewhitelist.domain.Whitelist;

public interface WhitelistServiceAuditable {

    public Optional<Whitelist> findByMsisdn(String msisd, Locale locale);

    public List<Whitelist> findAll(Locale locale);

    public Whitelist save(Whitelist whitelist, Locale locale);

    public Long maxId();

}

package zw.co.econet.servicewhitelist.business.auditables.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import zw.co.econet.servicewhitelist.business.auditables.api.WhitelistServiceAuditable;
import zw.co.econet.servicewhitelist.domain.Whitelist;
import zw.co.econet.servicewhitelist.repository.WhitelistRepository;

public class WhitelistServiceAuditableImpl implements WhitelistServiceAuditable {

    public WhitelistRepository whitelistRepository;

    public WhitelistServiceAuditableImpl(WhitelistRepository whitelistRepository) {
        this.whitelistRepository = whitelistRepository;
    }

    @Override
    public Optional<Whitelist> findByMsisdn(String msisd, Locale locale) {
        return whitelistRepository.findByMsisdn(msisd);
    }

    @Override
    public List<Whitelist> findAll(Locale locale) {
        return whitelistRepository.findAll();
    }

    @Override
    public Whitelist save(Whitelist whitelist, Locale locale) {
        return whitelistRepository.save(whitelist);
    }

    @Override
    public Long maxId() {
        return whitelistRepository.lastInsertedId();
    }
}

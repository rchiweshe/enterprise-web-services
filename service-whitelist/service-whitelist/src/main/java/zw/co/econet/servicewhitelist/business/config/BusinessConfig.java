package zw.co.econet.servicewhitelist.business.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import zw.co.econet.commons.msisdn.parser.MsisdnParser;
import zw.co.econet.enterprise.web.services.common.utils.config.UtilsConfig;
import zw.co.econet.enterprise.web.services.common.utils.i18.api.MessageService;
import zw.co.econet.servicewhitelist.business.auditables.api.WhitelistServiceAuditable;
import zw.co.econet.servicewhitelist.business.auditables.impl.WhitelistServiceAuditableImpl;
import zw.co.econet.servicewhitelist.business.logic.api.WhitelistService;
import zw.co.econet.servicewhitelist.business.logic.impl.WhitelistServiceImpl;
import zw.co.econet.servicewhitelist.repository.WhitelistRepository;
import zw.co.econet.servicewhitelist.utils.mapper.WhitelistMapper;

@Configuration
@Import({UtilsConfig.class})
public class BusinessConfig {

    @Bean
    public WhitelistServiceAuditable whitelistServiceAuditable(WhitelistRepository whitelistRepository){
        return new WhitelistServiceAuditableImpl(whitelistRepository);
    }

    @Bean
    public WhitelistMapper whitelistMapper(){return Mappers.getMapper(WhitelistMapper.class);}

    @Bean
    public WhitelistService whitelistService(WhitelistMapper whitelistMapper, WhitelistServiceAuditable whitelistServiceAuditable,
                                             MessageService messageService, MsisdnParser msisdnParser){
        return new WhitelistServiceImpl(whitelistMapper, whitelistServiceAuditable, messageService, msisdnParser);
    }


}

package zw.co.econet.servicewhitelist.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import zw.co.econet.servicewhitelist.business.config.BusinessConfig;
import zw.co.econet.servicewhitelist.business.logic.api.WhitelistService;
import zw.co.econet.servicewhitelist.service.processors.api.WhitelistProcessor;
import zw.co.econet.servicewhitelist.service.processors.impl.WhitelistProcessorImpl;

@Configuration
@Import({BusinessConfig.class})
public class ServiceConfig {

    @Bean
    public WhitelistProcessor whitelistProcessor(WhitelistService whitelistService){
        return new WhitelistProcessorImpl(whitelistService);
    }

}

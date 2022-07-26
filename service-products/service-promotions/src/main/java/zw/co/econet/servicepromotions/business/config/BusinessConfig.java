package zw.co.econet.servicepromotions.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import zw.co.econet.servicepromotions.business.auditables.api.PromotionsServiceAuditable;
import zw.co.econet.servicepromotions.business.auditables.impl.PromotionsServiceAuditableImpl;
import zw.co.econet.servicepromotions.repository.PromotionsRepository;
import zw.co.econet.servicepromotions.repository.config.DataConfig;

@Configuration
@Import({DataConfig.class})
public class BusinessConfig {

    @Bean
    public PromotionsServiceAuditable promotionsServiceAuditable(PromotionsRepository promotionsRepository){
        return new PromotionsServiceAuditableImpl(promotionsRepository);
    }

}

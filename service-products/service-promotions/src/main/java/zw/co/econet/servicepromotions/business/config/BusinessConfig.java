package zw.co.econet.servicepromotions.business.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import zw.co.econet.enterprise.web.services.common.utils.config.UtilsConfig;
import zw.co.econet.enterprise.web.services.common.utils.i18.api.MessageService;
import zw.co.econet.servicepromotions.business.auditables.api.PromotionsServiceAuditable;
import zw.co.econet.servicepromotions.business.auditables.impl.PromotionsServiceAuditableImpl;
import zw.co.econet.servicepromotions.business.logic.api.PromotionService;
import zw.co.econet.servicepromotions.business.logic.impl.PromotionsServiceImpl;
import zw.co.econet.servicepromotions.business.validations.api.PromotionsServiceValidator;
import zw.co.econet.servicepromotions.business.validations.impl.PromotionsServiceValidatorImpl;
import zw.co.econet.servicepromotions.repository.PromotionsRepository;
import zw.co.econet.servicepromotions.repository.config.DataConfig;

@Configuration
@Import({DataConfig.class, UtilsConfig.class})
public class BusinessConfig {

    @Bean
    public PromotionsServiceAuditable promotionsServiceAuditable(PromotionsRepository promotionsRepository) {
        return new PromotionsServiceAuditableImpl(promotionsRepository);
    }

    @Bean
    public PromotionsServiceValidator promotionsServiceValidator() {
        return new PromotionsServiceValidatorImpl();
    }

    @Bean
    public PromotionService promotionService(PromotionsServiceValidator promotionsServiceValidator,
                                             PromotionsRepository promotionsRepository,
                                             ModelMapper modelMapper,
                                             PromotionsServiceAuditable promotionsServiceAuditable,
                                             MessageService messageService) {
        return new PromotionsServiceImpl(promotionsServiceValidator, promotionsRepository, modelMapper,
                promotionsServiceAuditable, messageService);
    }
}

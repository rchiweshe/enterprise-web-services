package zw.co.econet.servicepromotions.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zw.co.econet.servicepromotions.business.logic.api.PromotionService;
import zw.co.econet.servicepromotions.service.processors.api.PromotionsProcessor;
import zw.co.econet.servicepromotions.service.processors.impl.PromotionsProcessorImpl;

@Configuration
public class ServiceConfig {

    @Bean
    public PromotionsProcessor promotionsProcessor(PromotionService promotionService) {
        return new PromotionsProcessorImpl(promotionService);
    }

}

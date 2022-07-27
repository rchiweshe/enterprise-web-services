package zw.co.econet.servicepromotions.business.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import zw.co.econet.servicepromotions.business.auditables.api.PromotionsServiceAuditable;
import zw.co.econet.servicepromotions.business.auditables.impl.PromotionsServiceAuditableImpl;
import zw.co.econet.servicepromotions.repository.PromotionsRepository;
import zw.co.econet.servicepromotions.repository.config.DataConfig;
import zw.co.econet.servicepromotions.util.i18.api.MessageService;
import zw.co.econet.servicepromotions.util.i18.impl.MessageServiceImpl;

@Configuration
@Import({DataConfig.class})
public class BusinessConfig {

    @Bean
    public PromotionsServiceAuditable promotionsServiceAuditable(PromotionsRepository promotionsRepository){
        return new PromotionsServiceAuditableImpl(promotionsRepository);
    }


    @Bean(name = "customMessageSource")
    public MessageSource customMessageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl(customMessageSource());
    }


}

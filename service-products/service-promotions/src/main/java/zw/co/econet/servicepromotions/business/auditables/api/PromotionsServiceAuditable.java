package zw.co.econet.servicepromotions.business.auditables.api;

import java.util.Locale;
import zw.co.econet.servicepromotions.domain.Promotion;

public interface PromotionsServiceAuditable {
    public Promotion createPromotion(Promotion promotions, Locale locale, String username);

}





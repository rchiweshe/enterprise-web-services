package zw.co.econet.servicepromotions.business.auditables.api;

import java.util.Locale;
import zw.co.econet.servicepromotions.domain.Promotions;

public interface PromotionsServiceAuditable {
    public Promotions createPromotion(Promotions promotions, Locale locale, String username);
}





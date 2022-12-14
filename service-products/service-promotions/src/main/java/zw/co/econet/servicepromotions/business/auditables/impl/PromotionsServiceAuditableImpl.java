package zw.co.econet.servicepromotions.business.auditables.impl;

import java.util.Locale;
import zw.co.econet.servicepromotions.business.auditables.api.PromotionsServiceAuditable;
import zw.co.econet.servicepromotions.domain.Promotion;
import zw.co.econet.servicepromotions.repository.PromotionsRepository;

public class PromotionsServiceAuditableImpl implements PromotionsServiceAuditable {

    private PromotionsRepository promotionsRepository;

    public PromotionsServiceAuditableImpl(PromotionsRepository promotionsRepository) {
        this.promotionsRepository = promotionsRepository;
    }

    @Override
    public Promotion createPromotion(Promotion promotions, Locale locale, String username) {
        return promotionsRepository.save(promotions);
    }
}

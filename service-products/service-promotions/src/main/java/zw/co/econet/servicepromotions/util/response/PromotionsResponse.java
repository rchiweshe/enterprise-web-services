package zw.co.econet.servicepromotions.util.response;

import java.util.List;
import zw.co.econet.enterprise.web.services.common.utils.response.CommonResponse;
import zw.co.econet.servicepromotions.util.dto.PromotionDto;

public class PromotionsResponse extends CommonResponse {

    private PromotionDto promotionDto;
    private List<PromotionDto> promotionDtoList;

    public PromotionDto getPromotionDto() {
        return promotionDto;
    }

    public void setPromotionDto(PromotionDto promotionDto) {
        this.promotionDto = promotionDto;
    }

    public List<PromotionDto> getPromotionDtoList() {
        return promotionDtoList;
    }

    public void setPromotionDtoList(List<PromotionDto> promotionDtoList) {
        this.promotionDtoList = promotionDtoList;
    }

    @Override
    public String toString() {
        return "PromotionsResponse{" + "promotionDto=" + promotionDto + ", promotionDtoList=" + promotionDtoList + '}';
    }
}

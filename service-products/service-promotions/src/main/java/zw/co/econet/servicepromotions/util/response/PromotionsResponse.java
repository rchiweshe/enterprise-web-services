package zw.co.econet.servicepromotions.util.response;

import java.util.List;
import zw.co.econet.servicepromotions.util.dto.PromotionDto;

public class PromotionsResponse {

    private int statusCode;
    private Boolean success;
    private String message;
    private PromotionDto promotionDto;
    private List<PromotionDto> promotionDtoList;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
        return "PromotionsResponse{" + "statusCode=" + statusCode +
                ", success=" + success +
                ", message='" + message + '\''
                + ", promotionDto=" + promotionDto +
                ", promotionDtoList=" + promotionDtoList +
                '}';
    }
}

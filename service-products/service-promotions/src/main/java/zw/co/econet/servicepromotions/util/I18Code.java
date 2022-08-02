package zw.co.econet.servicepromotions.util;

public enum I18Code {

    MESSAGE_PROMOTION_CREATED_SUCCESSFULLY("messages.promotion.created.successfully"),
    MESSAGE_PROMOTION_INVALID_REQUEST("messages.promotion.invalid.request"),
    MESSAGE_PROMOTION_EXISTS("messages.promotion.already.exists");

    private String code;

    I18Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
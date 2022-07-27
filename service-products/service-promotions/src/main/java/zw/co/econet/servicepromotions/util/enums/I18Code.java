package zw.co.econet.servicepromotions.util.enums;

public enum I18Code {

    MESSAGE_PROMOTION_CREATED_SUCCESSFULLY("messages.promotion.created.successfully");

    private String code;

    I18Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

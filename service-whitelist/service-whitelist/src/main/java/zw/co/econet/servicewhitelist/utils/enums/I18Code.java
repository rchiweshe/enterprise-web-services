package zw.co.econet.servicewhitelist.utils.enums;

public enum I18Code {
    MESSAGE_INVALID_MSISDN_SEARCH_REQUEST("messages.invalid.msisdn.search.request"),
    MESSAGE_MSISDN_NOT_WHITELISTED("messages.msisdn.not.whitelisted"),
    MESSAGE_WHITELISTED_MSISDN_SUCCESSFULLY_RETRIEVED("messages.whitelisted.msisdn.successfully.retrieved"),
    MESSAGE_WHITELISTED_NUMBERS_NOT_FOUND("messages.whitelisted.numbers.not.found"),
    MESSAGE_WHITELISTED_NUMBERS_RETRIEVED_SUCCESS("messages.whitelisted.numbers.successfully.retrieved"),
    MESSAGE_MSISDN_REQUIRED("message.msisdn.required"),
    MESSAGE_INVALID_MSISDN_SUPPLIED("messages.invalid.msisdn.supplied"),
    MESSAGE_MSISDN_ALREADY_EXISTS("messages.msisdn.already.exists"),
    MESSAGE_MSISDN_SUCCESSFULLY_WHITELISTED("msisdn.successfully.whitelisted");

    private String code;

    I18Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
package zw.co.econet.servicewhitelist.utils.response;

import java.util.List;
import zw.co.econet.servicewhitelist.utils.dto.WhitelistDto;

public class WhiteListResponse {

    private boolean success;
    private String narrative;
    private int statusCode;
    private String message;

    private WhitelistDto whitelistDto;

    private List<WhitelistDto> whitelistDtoList;

    public boolean isSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WhitelistDto getWhitelistDto() {
        return whitelistDto;
    }

    public void setWhitelistDto(WhitelistDto whitelistDto) {
        this.whitelistDto = whitelistDto;
    }

    public List<WhitelistDto> getWhitelistDtoList() {
        return whitelistDtoList;
    }

    public void setWhitelistDtoList(List<WhitelistDto> whitelistDtoList) {
        this.whitelistDtoList = whitelistDtoList;
    }

    @Override
    public String toString() {
        return "WhiteListResponse{" +
                "success=" + success +
                ", narrative='" + narrative + '\'' +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", whitelistDto=" + whitelistDto +
                ", whitelistDtoList=" + whitelistDtoList +
                '}';
    }
}

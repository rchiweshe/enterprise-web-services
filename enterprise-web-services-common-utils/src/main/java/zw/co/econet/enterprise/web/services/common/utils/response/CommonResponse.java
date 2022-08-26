package zw.co.econet.enterprise.web.services.common.utils.response;

public class CommonResponse {

    private int statusCode;
    private boolean success;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResponse{" + "statusCode=" + statusCode + ", success=" + success + ", message='" + message + '\'' + '}';
    }
}

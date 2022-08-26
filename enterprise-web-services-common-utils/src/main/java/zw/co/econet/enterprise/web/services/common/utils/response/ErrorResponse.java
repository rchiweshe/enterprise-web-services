package zw.co.econet.enterprise.web.services.common.utils.response;

public class ErrorResponse {
 
    private int statusCode;
    private String message;
 
    public ErrorResponse(int statusCode, String message)
    {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }
}
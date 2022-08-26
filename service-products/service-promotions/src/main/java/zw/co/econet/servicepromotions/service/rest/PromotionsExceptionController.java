package zw.co.econet.servicepromotions.service.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.co.econet.enterprise.web.services.common.utils.response.ErrorResponse;
import zw.co.econet.servicepromotions.util.response.PromotionsResponse;

@RestControllerAdvice
public class PromotionsExceptionController extends ResponseEntityExceptionHandler {

   private Logger logger = LoggerFactory.getLogger(this.getClass());

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(value = Exception.class)
   public PromotionsResponse handleException2(Exception ex) {
      logger.error("Error while processing promotions API request : {}", ex.getMessage());
      PromotionsResponse promotionsResponse = new PromotionsResponse();
      promotionsResponse.setMessage("Sorry, We encountered an error while processing promotions API request");
      promotionsResponse.setStatusCode(501);
      promotionsResponse.setSuccess(false);
      return promotionsResponse;
   }

//   @ResponseStatus(HttpStatus.BAD_REQUEST)
//   @ExceptionHandler(MethodArgumentNotValidException.class)
//   public Map<String, String> handleValidationExceptions(
//           MethodArgumentNotValidException ex) {
//      Map<String, String> errors = new HashMap<>();
//      ex.getBindingResult().getAllErrors().forEach((error) -> {
//         String fieldName = ((FieldError) error).getField();
//         String errorMessage = error.getDefaultMessage();
//         errors.put(fieldName, errorMessage);
//      });
//      return errors;
//   }
//
//   @Override
//   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                 HttpHeaders headers, HttpStatus status, WebRequest request) {
//      String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
//      List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(
//              Collectors.toList());
//      logger.info("Validation error list : "+validationList);
//      ErrorResponse errorResponse = new ErrorResponse();
//      errorResponse.s
//      apiErrorVO.setErrorList(validationList);
//      return new ResponseEntity<>(apiErrorVO, status);
//   }


}
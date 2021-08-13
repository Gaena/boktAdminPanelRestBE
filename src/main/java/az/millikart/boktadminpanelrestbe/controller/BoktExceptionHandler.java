package az.millikart.boktadminpanelrestbe.controller;

import az.millikart.boktadminpanelrestbe.domain.ErrorResponse;
import az.millikart.boktadminpanelrestbe.exception.CustomException;
import az.millikart.boktadminpanelrestbe.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BoktExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleRuntimeException(RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getClass().getSimpleName() + ":" + exception.getMessage());
        return errorResponse;
    }

   /* @ExceptionHandler({UserNotFoundException.class, CustomException.class})
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setCode(104);
        return errorResponse;
    }*/

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Internal Error");
        errorResponse.setCode(105);
        return errorResponse;
    }
}

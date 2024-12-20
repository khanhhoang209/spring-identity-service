package com.fpt.identityservice.exeption;

import com.fpt.identityservice.schema.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ServiceResponse> handleRuntimeException(Exception exception) {
        var serviceResponse = new ServiceResponse();
        serviceResponse
                .setSucceeded(false)
                .setStatusCode(HttpStatus.BAD_REQUEST)
                .setResponseCode(HttpStatus.BAD_REQUEST.value())
                .addDetail("message", exception.getMessage())
                .addError("invalidCredentials", "Invalid credentials provided!");
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ServiceResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var serviceResponse = new ServiceResponse();
        serviceResponse
                .setSucceeded(false)
                .setStatusCode(HttpStatus.BAD_REQUEST)
                .setResponseCode(HttpStatus.BAD_REQUEST.value())
                .addDetail("message", exception.getFieldError().getDefaultMessage())
                .addError("invalidCredentials", "Invalid credentials provided!");
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @ExceptionHandler(value = NullPointerException.class)
    ResponseEntity<ServiceResponse> handleNullPointerException(NullPointerException exception) {
        var serviceResponse = new ServiceResponse();
        serviceResponse
                .setSucceeded(false)
                .setStatusCode(HttpStatus.NOT_FOUND)
                .setResponseCode(HttpStatus.NOT_FOUND.value())
                .addDetail("message", exception.getMessage())
                .addError("notFound" ,"The requested resource could not be found!");
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    ResponseEntity<ServiceResponse> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        var serviceResponse = new ServiceResponse();
        serviceResponse
                .setSucceeded(false)
                .setStatusCode(HttpStatus.NOT_FOUND)
                .setResponseCode(HttpStatus.NOT_FOUND.value())
                .addDetail("message", exception.getMessage())
                .addError("notFound", "The requested endpoint could not be found!");
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ServiceResponse> handleException(Exception exception) {
        var serviceResponse = new ServiceResponse();
        serviceResponse
                .setSucceeded(false)
                .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .addDetail("message", exception.getMessage())
                .addError("outOfService" ,"Internal Server Error!");
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

}

package com.api.Booking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ExceptionErrorDetails> BookingNotFoundHandleException(BookingNotFoundException ex , WebRequest request){
        ExceptionErrorDetails exceptionErrorDetails =
                new ExceptionErrorDetails(request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(exceptionErrorDetails,ex.getHttpStatus());
    }

    @ExceptionHandler(FlightOrSeatsNumException.class)
    public ResponseEntity<ExceptionErrorDetails> FlightOrSeatsNumHandleException(FlightOrSeatsNumException ex , WebRequest request){
        ExceptionErrorDetails exceptionErrorDetails =
                new ExceptionErrorDetails(request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(exceptionErrorDetails,ex.getHttpStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionErrorDetails> UserNotFoundHandleException(UserNotFoundException ex , WebRequest request){
        ExceptionErrorDetails exceptionErrorDetails =
                new ExceptionErrorDetails(request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(exceptionErrorDetails,ex.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDetails> handleValidationException(MethodArgumentNotValidException ex, WebRequest request){
        ValidationErrorDetails validationErrors = new ValidationErrorDetails(request.getDescription(false));
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError f : fieldErrors){
            validationErrors.addError(f.getDefaultMessage());
        }
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);

    }

}

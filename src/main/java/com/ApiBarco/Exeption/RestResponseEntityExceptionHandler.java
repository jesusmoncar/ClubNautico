package com.ApiBarco.Exeption;

import com.ApiBarco.Exeption.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ClubNauticoNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> NotFound ( ClubNauticoNotFoundException memberNotFound){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,memberNotFound.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }

}

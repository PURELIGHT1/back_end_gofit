package com.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.exception.instruktur.InstrukturExceptionBadRequest;
import com.api.exception.instruktur.InstrukturExceptionNotFound;
import com.api.exception.response.ExceptionResponse;

@ControllerAdvice
public class InstrukturExceptionHandler {

    @ExceptionHandler(value = { InstrukturExceptionBadRequest.class })
    public ResponseEntity<Object> handlerPromoException(InstrukturExceptionBadRequest instrukturExceptionEmpty) {
        ExceptionResponse instrukturExceptionResponse = new ExceptionResponse(
                instrukturExceptionEmpty.getMessage(),
                instrukturExceptionEmpty.getCause(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(instrukturExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { InstrukturExceptionNotFound.class })
    public ResponseEntity<Object> handlerPromoException(InstrukturExceptionNotFound instrukturExceptionNotFound) {
        ExceptionResponse instrukturExceptionResponse = new ExceptionResponse(
                instrukturExceptionNotFound.getMessage(),
                instrukturExceptionNotFound.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(instrukturExceptionResponse, HttpStatus.NOT_FOUND);
    }
}

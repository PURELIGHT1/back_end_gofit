package com.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.exception.kelas.*;
import com.api.exception.response.ExceptionResponse;

@ControllerAdvice
public class KelasExceptionHandler {

    @ExceptionHandler(value = { KelasExceptionNotFound.class })
    public ResponseEntity<Object> handlerKelasException(KelasExceptionNotFound kelasExceptionNotFound) {
        ExceptionResponse kelasExceptionResponse = new ExceptionResponse(
                kelasExceptionNotFound.getMessage(),
                kelasExceptionNotFound.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(kelasExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { KelasExceptionBadRequest.class })
    public ResponseEntity<Object> handlerKelasException(KelasExceptionBadRequest kelasExceptionEmpty) {
        ExceptionResponse kelasExceptionResponse = new ExceptionResponse(
                kelasExceptionEmpty.getMessage(),
                kelasExceptionEmpty.getCause(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(kelasExceptionResponse, HttpStatus.NOT_FOUND);
    }

}

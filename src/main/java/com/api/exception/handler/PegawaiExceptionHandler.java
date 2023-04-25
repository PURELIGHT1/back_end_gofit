package com.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.exception.pegawai.*;
import com.api.exception.response.ExceptionResponse;

@ControllerAdvice
public class PegawaiExceptionHandler {

    @ExceptionHandler(value = { PegawaiExceptionBadRequest.class })
    public ResponseEntity<Object> handlerPromoException(PegawaiExceptionBadRequest PegawaiExceptionEmpty) {
        ExceptionResponse PegawaiExceptionResponse = new ExceptionResponse(
                PegawaiExceptionEmpty.getMessage(),
                PegawaiExceptionEmpty.getCause(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(PegawaiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { PegawaiExceptionNotFound.class })
    public ResponseEntity<Object> handlerPromoException(PegawaiExceptionNotFound PegawaiExceptionNotFound) {
        ExceptionResponse PegawaiExceptionResponse = new ExceptionResponse(
                PegawaiExceptionNotFound.getMessage(),
                PegawaiExceptionNotFound.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(PegawaiExceptionResponse, HttpStatus.NOT_FOUND);
    }
}

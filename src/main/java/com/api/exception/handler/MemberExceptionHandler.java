package com.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.exception.member.*;
import com.api.exception.response.ExceptionResponse;

@ControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(value = { MemberExceptionBadRequest.class })
    public ResponseEntity<Object> handlerPromoException(MemberExceptionBadRequest MemberExceptionEmpty) {
        ExceptionResponse MemberExceptionResponse = new ExceptionResponse(
                MemberExceptionEmpty.getMessage(),
                MemberExceptionEmpty.getCause(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(MemberExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MemberExceptionNotFound.class })
    public ResponseEntity<Object> handlerPromoException(MemberExceptionNotFound MemberExceptionNotFound) {
        ExceptionResponse MemberExceptionResponse = new ExceptionResponse(
                MemberExceptionNotFound.getMessage(),
                MemberExceptionNotFound.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(MemberExceptionResponse, HttpStatus.NOT_FOUND);
    }
}

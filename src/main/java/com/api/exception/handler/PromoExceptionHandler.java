package com.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.exception.promo.PromoExceptionBadRequest;
import com.api.exception.promo.PromoExceptionNotFound;
import com.api.exception.response.ExceptionResponse;

@ControllerAdvice
public class PromoExceptionHandler {

    @ExceptionHandler(value = { PromoExceptionNotFound.class })
    public ResponseEntity<Object> handlerPromoException(PromoExceptionNotFound promoExceptionNotFound) {
        ExceptionResponse promoExceptionResponse = new ExceptionResponse(
                promoExceptionNotFound.getMessage(),
                promoExceptionNotFound.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(promoExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { PromoExceptionBadRequest.class })
    public ResponseEntity<Object> handlerPromoException(PromoExceptionBadRequest promoExceptionEmpty) {
        ExceptionResponse promoExceptionResponse = new ExceptionResponse(
                promoExceptionEmpty.getMessage(),
                promoExceptionEmpty.getCause(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(promoExceptionResponse, HttpStatus.NOT_FOUND);
    }

}

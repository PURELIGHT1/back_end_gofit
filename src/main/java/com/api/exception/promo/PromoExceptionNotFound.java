package com.api.exception.promo;

public class PromoExceptionNotFound extends RuntimeException {

    public PromoExceptionNotFound(String message) {
        super(message);
    }

    public PromoExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}

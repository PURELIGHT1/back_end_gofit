package com.api.exception.promo;

public class PromoExceptionBadRequest extends RuntimeException {

    public PromoExceptionBadRequest(String message) {
        super(message);
    }

    public PromoExceptionBadRequest(String message, Throwable cause) {
        super(message, cause);
    }

}

package com.api.exception.kelas;

public class KelasExceptionBadRequest extends RuntimeException {

    public KelasExceptionBadRequest(String message) {
        super(message);
    }

    public KelasExceptionBadRequest(String message, Throwable cause) {
        super(message, cause);
    }

}

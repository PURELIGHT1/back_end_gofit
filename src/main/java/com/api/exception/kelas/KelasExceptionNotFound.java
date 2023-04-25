package com.api.exception.kelas;

public class KelasExceptionNotFound extends RuntimeException {

    public KelasExceptionNotFound(String message) {
        super(message);
    }

    public KelasExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}

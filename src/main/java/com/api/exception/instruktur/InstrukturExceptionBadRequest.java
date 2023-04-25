package com.api.exception.instruktur;

public class InstrukturExceptionBadRequest extends RuntimeException {

    public InstrukturExceptionBadRequest(String message) {
        super(message);
    }

    public InstrukturExceptionBadRequest(String message, Throwable cause) {
        super(message, cause);
    }

}

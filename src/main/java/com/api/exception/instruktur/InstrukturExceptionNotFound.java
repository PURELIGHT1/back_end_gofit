package com.api.exception.instruktur;

public class InstrukturExceptionNotFound extends RuntimeException {

    public InstrukturExceptionNotFound(String message) {
        super(message);
    }

    public InstrukturExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}

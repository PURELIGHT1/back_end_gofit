package com.api.exception.pegawai;

public class PegawaiExceptionNotFound extends RuntimeException {

    public PegawaiExceptionNotFound(String message) {
        super(message);
    }

    public PegawaiExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}

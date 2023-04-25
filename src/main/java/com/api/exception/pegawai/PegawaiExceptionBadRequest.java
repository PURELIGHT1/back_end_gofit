package com.api.exception.pegawai;

public class PegawaiExceptionBadRequest extends RuntimeException {

    public PegawaiExceptionBadRequest(String message) {
        super(message);
    }

    public PegawaiExceptionBadRequest(String message, Throwable cause) {
        super(message, cause);
    }

}

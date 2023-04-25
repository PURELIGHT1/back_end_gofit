package com.api.exception.member;

public class MemberExceptionBadRequest extends RuntimeException {

    public MemberExceptionBadRequest(String message) {
        super(message);
    }

    public MemberExceptionBadRequest(String message, Throwable cause) {
        super(message, cause);
    }

}

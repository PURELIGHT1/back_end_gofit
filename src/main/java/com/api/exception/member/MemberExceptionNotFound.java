package com.api.exception.member;

public class MemberExceptionNotFound extends RuntimeException {

    public MemberExceptionNotFound(String message) {
        super(message);
    }

    public MemberExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}

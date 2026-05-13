package com.example.fitnessmembership.exception;

public class MembershipNotFoundException extends RuntimeException {

    public MembershipNotFoundException(String message) {
        super(message);
    }

    public MembershipNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

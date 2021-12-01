package com.example.loginservice.Exception;

public class CredentialsExpiredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CredentialsExpiredException(String message) {

        super(message);
    }
}
package com.portal.backend.clientinterviewtracker.exception;

public class TokenNotFoundException extends RuntimeException{

    public TokenNotFoundException(String message){
        super(message);
    }
}

package com.hospital_manager.services.exceptions;

public class LoginIsBusyException extends ServiceException{

    public LoginIsBusyException(String message) {
        super(message);
    }
}

package com.bilgeadam.exception.custom;

import com.bilgeadam.exception.EErrorType;

public class UserEmailExistsException extends RuntimeException{
    final EErrorType errorType;

    public UserEmailExistsException(EErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}

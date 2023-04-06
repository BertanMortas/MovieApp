package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class MovieAppException extends RuntimeException{
    private final EErrorType errorType;
    public MovieAppException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }
    public MovieAppException(EErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}

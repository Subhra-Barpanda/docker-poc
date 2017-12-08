package com.newtechnology.poc.exception;

public class ApplicationRunTimeException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public ApplicationRunTimeException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

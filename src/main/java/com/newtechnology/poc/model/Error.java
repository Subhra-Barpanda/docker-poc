package com.newtechnology.poc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error {
    private String errorCode;
    private String errorMessage;

    public Error() {
    }

    public Error(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Error error = (Error) o;

        if (!getErrorCode().equals(error.getErrorCode())) return false;
        return getErrorMessage().equals(error.getErrorMessage());
    }

    @Override
    public int hashCode() {
        int result = getErrorCode().hashCode();
        result = 31 * result + getErrorMessage().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Error{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

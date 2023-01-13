package org.cdg.exception;


public class InvalidArgsException extends RuntimeException {
    public InvalidArgsException(String errorMessage) {
        super(errorMessage);
    }

}

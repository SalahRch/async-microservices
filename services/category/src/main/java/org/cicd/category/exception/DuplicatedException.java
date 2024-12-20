package org.cicd.category.exception;

public class DuplicatedException extends RuntimeException{
    public DuplicatedException(){

    }
    public DuplicatedException(String message) {
        super(message);
    }

    public DuplicatedException(Throwable cause) {
        super(cause);
    }

    public DuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

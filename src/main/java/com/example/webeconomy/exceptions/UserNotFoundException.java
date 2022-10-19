package com.example.webeconomy.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3808246479819405630L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

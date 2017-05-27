package com.antonbelev.jpmmessageprocessor.exceptions;

public class EventHandlerException extends Exception {
    public EventHandlerException(String message) {
        super(message);
    }

    public EventHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}

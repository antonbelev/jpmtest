package com.antonbelev.jpmmessageprocessor.exceptions;

public class EmptyMessageQueueException extends Exception {
    public EmptyMessageQueueException(String message) {
        super(message);
    }

    public EmptyMessageQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}

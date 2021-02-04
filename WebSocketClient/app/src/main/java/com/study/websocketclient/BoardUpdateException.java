package com.study.websocketclient;

public class BoardUpdateException extends RuntimeException {
    public BoardUpdateException(String message) {
        super(message);
    }

    public BoardUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardUpdateException(Throwable cause) {
        super(cause);
    }
}

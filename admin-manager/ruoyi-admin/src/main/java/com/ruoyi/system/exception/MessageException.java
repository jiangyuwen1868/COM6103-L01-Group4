package com.ruoyi.system.exception;

public class MessageException extends CspException {
    private static final long serialVersionUID = 1L;

    public MessageException() {
        super();
    }

    public MessageException(int code, String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(int code, String message) {
        super(code, message);
    }

    public MessageException(int code, Throwable cause) {
        super(code, cause);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(int code) {
        super(code);
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }
}

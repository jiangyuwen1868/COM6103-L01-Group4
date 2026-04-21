package com.ruoyi.system.exception;

public class MessageParseException extends CspException {
    private static final long serialVersionUID = 1L;

    public MessageParseException() {
        super();
    }

    public MessageParseException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public MessageParseException(int code, String message) {
        super(code, message);
    }

    public MessageParseException(int code, Throwable cause) {
        super(code, cause);
    }

    public MessageParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageParseException(int code) {
        super(code);
    }

    public MessageParseException(String message) {
        super(message);
    }

    public MessageParseException(Throwable cause) {
        super(cause);
    }
}

package com.ruoyi.system.exception;

public class CspException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;

    public CspException() {
        super();
        this.code = -1;
    }

    public CspException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CspException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CspException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public CspException(String message, Throwable cause) {
        super(message, cause);
        this.code = -1;
    }

    public CspException(int code) {
        this.code = code;
    }

    public CspException(String message) {
        super(message);
        this.code = -1;
    }

    public CspException(Throwable cause) {
        super(cause);
        this.code = -1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

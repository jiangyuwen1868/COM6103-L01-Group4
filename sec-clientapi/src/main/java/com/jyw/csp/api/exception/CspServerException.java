package com.jyw.csp.api.exception;

public class CspServerException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code;

    public CspServerException() {
        super();
    }

    public CspServerException(String message) {
        super(message);
    }

    public CspServerException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CspServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CspServerException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CspServerException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

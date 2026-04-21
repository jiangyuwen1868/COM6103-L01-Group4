package com.jyw.csp.api.exception;

public class HsmCommandException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code;

    public HsmCommandException() {
        super();
    }

    public HsmCommandException(String message) {
        super(message);
    }

    public HsmCommandException(String code, String message) {
        super(message);
        this.code = code;
    }

    public HsmCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public HsmCommandException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public HsmCommandException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

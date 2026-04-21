package com.ruoyi.system.exception;

public interface IndexedMessage {
    public String getCode();

    public Object[] getParameters();

    public String getMessage();

    public Throwable getThrowable();
}

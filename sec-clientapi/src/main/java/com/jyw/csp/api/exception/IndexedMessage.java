package com.jyw.csp.api.exception;

public abstract interface IndexedMessage
{
  public abstract String getCode();

  public abstract Object[] getParameters();
  
  public abstract String getMessage();
  
  public abstract Throwable getThrowable();
}
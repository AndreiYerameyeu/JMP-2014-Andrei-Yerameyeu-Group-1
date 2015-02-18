package com.epam.edp.exception;


public abstract class BaseRuntimeException extends RuntimeException {
  
  private static final long serialVersionUID = 8418643099959800802L;
  
  public BaseRuntimeException() {
    super();
  }
  
  public BaseRuntimeException(String msg) {
    super(msg);
  }
  
  public BaseRuntimeException(Throwable ex) {
    super(ex);
  }
  
  public BaseRuntimeException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

package com.epam.edp.exception;


public abstract class BaseException extends Exception {
  
  private static final long serialVersionUID = -5992401678741843341L;
  
  public BaseException() {
    super();
  }
  
  public BaseException(String msg) {
    super(msg);
  }
  
  public BaseException(Throwable ex) {
    super(ex);
  }
  
  public BaseException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

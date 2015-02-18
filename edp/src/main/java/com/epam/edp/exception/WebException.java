package com.epam.edp.exception;


public class WebException extends BaseException {
  
  private static final long serialVersionUID = -2132295163343778978L;
  
  public WebException() {
    super();
  }
  
  public WebException(String msg) {
    super(msg);
  }
  
  public WebException(Throwable ex) {
    super(ex);
  }
  
  public WebException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

package com.epam.edp.exception;


public class WebCoreException extends BaseRuntimeException {
  
  private static final long serialVersionUID = 5034178132722655845L;
  
  public WebCoreException() {
    super();
  }
  
  public WebCoreException(String msg) {
    super(msg);
  }
  
  public WebCoreException(Throwable ex) {
    super(ex);
  }
  
  public WebCoreException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

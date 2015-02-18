package com.epam.edp.exception;


public class CoreException extends BaseRuntimeException {
  
  private static final long serialVersionUID = 9624142848060149L;
  
  public CoreException() {
    super();
  }
  
  public CoreException(String msg) {
    super(msg);
  }
  
  public CoreException(Throwable ex) {
    super(ex);
  }
  
  public CoreException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

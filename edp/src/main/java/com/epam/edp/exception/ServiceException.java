package com.epam.edp.exception;


public class ServiceException extends BaseException {
  
  private static final long serialVersionUID = 2705693465117989856L;
  
  public ServiceException() {
    super();
  }
  
  public ServiceException(String msg) {
    super(msg);
  }
  
  public ServiceException(Throwable ex) {
    super(ex);
  }
  
  public ServiceException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

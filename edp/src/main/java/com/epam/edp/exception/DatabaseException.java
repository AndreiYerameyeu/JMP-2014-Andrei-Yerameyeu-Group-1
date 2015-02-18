package com.epam.edp.exception;


public class DatabaseException extends BaseException {
  
  private static final long serialVersionUID = 298829836106752289L;
  
  public DatabaseException() {
    super();
  }
  
  public DatabaseException(String msg) {
    super(msg);
  }
  
  public DatabaseException(Throwable ex) {
    super(ex);
  }
  
  public DatabaseException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

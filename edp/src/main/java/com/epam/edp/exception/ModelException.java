package com.epam.edp.exception;

import java.util.HashMap;
import java.util.Map;


public class ModelException extends WebCoreException {
  
  private static final long serialVersionUID = -409818765501429320L;
  
  protected final Map<String, String> errorMessages = new HashMap<String, String>();
  
  public ModelException() {
    super();
  }
  
  public ModelException(String msg) {
    super(msg);
  }
  
  public ModelException(Throwable ex) {
    super(ex);
  }
  
  public ModelException(String msg, Throwable ex) {
    super(msg, ex);
  }
  
  public void addErrorMessage(String paramName, String message) {
    errorMessages.put(paramName, message);
  }
  
  public Map<String, String> getErrorMessages() {
    return errorMessages;
  }
  
}

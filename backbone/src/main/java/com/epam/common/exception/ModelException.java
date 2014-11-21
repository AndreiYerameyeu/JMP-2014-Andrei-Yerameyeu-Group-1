package com.epam.common.exception;

import java.util.HashMap;
import java.util.Map;


public class ModelException extends WebCoreException {
    
    private static final long serialVersionUID = -409818765501429320L;
    
    protected final Map<String, String> errorMessages = new HashMap<String, String>();
    
    public ModelException() {
        super();
    }
    
    public ModelException(String s) {
        super(s);
    }
    
    public ModelException(Throwable e) {
        super(e);
    }
    
    public ModelException(String s, Throwable e) {
        super(s, e);
    }
    
    public void addErrorMessage(String paramName, String message) {
        errorMessages.put(paramName, message);
    }
    
    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
    
}

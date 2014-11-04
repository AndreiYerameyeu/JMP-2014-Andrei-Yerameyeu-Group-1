package com.epam.common.exception;


public class ServiceException extends BaseException {
    
    private static final long serialVersionUID = 2705693465117989856L;
    
    public ServiceException() {
        super();
    }
    
    public ServiceException(String s) {
        super(s);
    }
    
    public ServiceException(Throwable e) {
        super(e);
    }
    
    public ServiceException(String s, Throwable e) {
        super(s, e);
    }
}

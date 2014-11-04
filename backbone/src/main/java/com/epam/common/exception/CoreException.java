package com.epam.common.exception;


public class CoreException extends BaseRuntimeException {
    
    private static final long serialVersionUID = 9624142848060149L;
    
    public CoreException() {
        super();
    }
    
    public CoreException(String s) {
        super(s);
    }
    
    public CoreException(Throwable e) {
        super(e);
    }
    
    public CoreException(String s, Throwable e) {
        super(s, e);
    }
}

package com.epam.common.exception;


public abstract class BaseRuntimeException extends RuntimeException {
    
    private static final long serialVersionUID = 8418643099959800802L;
    
    public BaseRuntimeException() {
        super();
    }
    
    public BaseRuntimeException(String s) {
        super(s);
    }
    
    public BaseRuntimeException(Throwable e) {
        super(e);
    }
    
    public BaseRuntimeException(String s, Throwable e) {
        super(s, e);
    }
}

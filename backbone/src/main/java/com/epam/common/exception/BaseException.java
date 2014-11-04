package com.epam.common.exception;


public abstract class BaseException extends Exception {
    
    private static final long serialVersionUID = -5992401678741843341L;
    
    public BaseException() {
        super();
    }
    
    public BaseException(String s) {
        super(s);
    }
    
    public BaseException(Throwable e) {
        super(e);
    }
    
    public BaseException(String s, Throwable e) {
        super(s, e);
    }
}

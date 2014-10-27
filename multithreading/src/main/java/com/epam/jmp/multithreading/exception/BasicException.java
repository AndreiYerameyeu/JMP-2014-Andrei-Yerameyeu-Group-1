package com.epam.jmp.multithreading.exception;


public class BasicException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public BasicException() {
        super();
    }
    
    public BasicException(String s) {
        super(s);
    }
    
    public BasicException(Throwable e) {
        super(e);
    }
    
    public BasicException(String s, Throwable e) {
        super(s, e);
    }
    
}

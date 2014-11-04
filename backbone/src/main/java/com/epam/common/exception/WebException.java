package com.epam.common.exception;


public class WebException extends BaseException {
    
    private static final long serialVersionUID = -2132295163343778978L;
    
    public WebException() {
        super();
    }
    
    public WebException(String s) {
        super(s);
    }
    
    public WebException(Throwable e) {
        super(e);
    }
    
    public WebException(String s, Throwable e) {
        super(s, e);
    }
}

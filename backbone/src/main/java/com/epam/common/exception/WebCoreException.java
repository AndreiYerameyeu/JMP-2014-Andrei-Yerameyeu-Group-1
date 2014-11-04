package com.epam.common.exception;


public class WebCoreException extends BaseRuntimeException {
    
    private static final long serialVersionUID = 5034178132722655845L;
    
    public WebCoreException() {
        super();
    }
    
    public WebCoreException(String s) {
        super(s);
    }
    
    public WebCoreException(Throwable e) {
        super(e);
    }
    
    public WebCoreException(String s, Throwable e) {
        super(s, e);
    }
}

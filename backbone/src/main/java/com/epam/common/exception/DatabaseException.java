package com.epam.common.exception;


public class DatabaseException extends BaseException {
    
    private static final long serialVersionUID = 298829836106752289L;
    
    public DatabaseException() {
        super();
    }
    
    public DatabaseException(String s) {
        super(s);
    }
    
    public DatabaseException(Throwable e) {
        super(e);
    }
    
    public DatabaseException(String s, Throwable e) {
        super(s, e);
    }
}

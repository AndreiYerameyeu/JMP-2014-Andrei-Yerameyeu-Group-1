package com.epam.web.view.model.error;

import java.io.Serializable;


public class ModelFieldErrorInfo implements Serializable {
    
    private static final long serialVersionUID = 6959571944652406528L;
    
    private String fieldName;
    private String fieldError;
    
    public ModelFieldErrorInfo() {
        
    }
    
    public ModelFieldErrorInfo(String fieldName, String fieldError) {
        this.fieldError = fieldError;
        this.fieldName = fieldName;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public String getFieldError() {
        return fieldError;
    }
    
    public void setFieldError(String fieldError) {
        this.fieldError = fieldError;
    }
    
}

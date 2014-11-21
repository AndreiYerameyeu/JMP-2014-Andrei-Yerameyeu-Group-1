package com.epam.web.view.model.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ModelErrorInfo implements Serializable {
    
    private static final long serialVersionUID = 3857959964202039723L;
    
    private String url;
    private String message;
    private final List<ModelFieldErrorInfo> modelFieldErrors = new ArrayList<ModelFieldErrorInfo>();
    
    public ModelErrorInfo() {
        
    }
    
    public ModelErrorInfo(String url, String message) {
        this.setUrl(url);
        this.setMessage(message);
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<ModelFieldErrorInfo> getModelFieldErrors() {
        return modelFieldErrors;
    }
    
    public void addModelFieldError(ModelFieldErrorInfo modelFieldErrorInfo) {
        modelFieldErrors.add(modelFieldErrorInfo);
    }
}

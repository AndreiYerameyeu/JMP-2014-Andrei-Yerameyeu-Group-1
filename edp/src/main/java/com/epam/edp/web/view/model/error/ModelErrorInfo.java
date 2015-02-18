package com.epam.edp.web.view.model.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ModelErrorInfo implements Serializable {
  
  private static final long serialVersionUID = 3857959964202039723L;
  
  private String url;
  private String methodType;
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
  
  public String getMethodType() {
    return methodType;
  }
  
  public void setMethodType(String methodType) {
    this.methodType = methodType;
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
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 11;
    return HashCodeBuilder.reflectionHashCode(hash, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

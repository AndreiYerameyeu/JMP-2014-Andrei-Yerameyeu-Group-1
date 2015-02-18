package com.epam.edp.web.view.model.error;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 13;
    return HashCodeBuilder.reflectionHashCode(hash, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

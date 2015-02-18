package com.epam.edp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class NumericId extends ObjectId {
  
  private static final long serialVersionUID = -6784289076137026549L;
  
  private final long value;
  
  public NumericId(String type, long value) {
    super(type);
    this.value = value;
  }
  
  @Override
  public String getValue() {
    return Long.toString(value);
  }
  
  @Override
  public long getNumericValue() {
    return value;
  }
  
  @Override
  public boolean isNumeric() {
    return true;
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 23;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

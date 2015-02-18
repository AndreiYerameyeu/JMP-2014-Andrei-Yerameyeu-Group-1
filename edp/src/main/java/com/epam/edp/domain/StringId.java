package com.epam.edp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;


public class StringId extends ObjectId {
  
  private static final long serialVersionUID = -6432699949186595565L;
  
  private final String value;
  
  public StringId(String type, String value) {
    super(type);
    Assert.notNull(value);
    this.value = value;
  }
  
  @Override
  public String getValue() {
    return value;
  }
  
  @Override
  public long getNumericValue() {
    return NumberUtils.toLong(value, -1);
  }
  
  @Override
  public boolean isNumeric() {
    return false;
  }
  
  @Override
  public int compareTo(Id obj) {
    return this.getValue().compareTo(obj.getValue());
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 11;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

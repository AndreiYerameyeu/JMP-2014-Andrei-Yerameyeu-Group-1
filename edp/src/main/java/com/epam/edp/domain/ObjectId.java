package com.epam.edp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;


public abstract class ObjectId implements Id {
  
  private static final long serialVersionUID = 3319184357228168801L;
  
  private final String objectType;
  
  public ObjectId(String objectType) {
    Assert.notNull(objectType, "ObjectType can't be null");
    this.objectType = objectType;
  }
  
  @Override
  public String getObjectType() {
    return objectType;
  }
  
  @Override
  public int compareTo(Id obj) {
    return Long.compare(this.getNumericValue(), obj.getNumericValue());
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 19;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

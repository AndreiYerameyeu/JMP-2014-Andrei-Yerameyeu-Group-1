package com.epam.edp.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BaseEntity<ID extends Id> implements Serializable {
  
  private static final long serialVersionUID = -2972989275646002005L;
  
  private ID id;
  
  public ID getId() {
    return this.id;
  }
  
  public void setId(ID id) {
    this.id = id;
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 17;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

package com.epam.edp.domain.ticket;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.epam.edp.domain.BaseEntity;
import com.epam.edp.domain.Id;
import com.epam.edp.domain.NumericId;


public class Ticket extends BaseEntity<NumericId> {
  
  private static final long serialVersionUID = -3153468241038708727L;
  
  private Id sessionId;
  private int row;
  private int position;
  
  public Id getSessionId() {
    return sessionId;
  }
  
  public void setSessionId(Id sessionId) {
    this.sessionId = sessionId;
  }
  
  public int getRow() {
    return row;
  }
  
  public void setRow(int row) {
    this.row = row;
  }
  
  public int getPosition() {
    return position;
  }
  
  public void setPosition(int position) {
    this.position = position;
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 29;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

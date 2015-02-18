package com.epam.edp.domain.session;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.epam.edp.domain.BaseEntity;
import com.epam.edp.domain.NumericId;
import com.epam.edp.util.TimeImpl;


public class Session extends BaseEntity<NumericId> {
  
  private static final long serialVersionUID = -7088188281368352053L;
  
  private String sessionId;
  private TimeImpl startTime;
  private String ip;
  
  public String getSessionId() {
    return sessionId;
  }
  
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
  
  public TimeImpl getStartTime() {
    return startTime;
  }
  
  public void setStartTime(TimeImpl startTime) {
    this.startTime = startTime;
  }
  
  public String getIp() {
    return ip;
  }
  
  public void setIp(String ip) {
    this.ip = ip;
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 31;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

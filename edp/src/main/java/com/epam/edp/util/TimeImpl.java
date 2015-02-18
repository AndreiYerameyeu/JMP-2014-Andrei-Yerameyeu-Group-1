package com.epam.edp.util;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

import com.epam.edp.json.TimeImplJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = TimeImplJsonSerializer.class)
public class TimeImpl implements Comparable<TimeImpl>, Cloneable, Serializable {
  
  private static final long serialVersionUID = 2046465505421084728L;
  
  private final LocalDateTime time;
  
  public TimeImpl(java.sql.Timestamp timestamp) {
    this(timestamp.toLocalDateTime());
  }
  
  public TimeImpl(LocalDateTime time) {
    Assert.notNull(time);
    this.time = time;
  }
  
  public TimeImpl(String date) {
    Assert.notNull(date);
    time = LocalDateTime.parse(date);
  }
  
  public static TimeImpl getNow() {
    return new TimeImpl(LocalDateTime.now());
  }
  
  public LocalDateTime getTime() {
    return time;
  }
  
  public java.sql.Timestamp toSqlTimestamp() {
    return java.sql.Timestamp.valueOf(time);
  }
  
  public java.util.Date toUtilDate() {
    return toSqlDate();
  }
  
  public java.sql.Date toSqlDate() {
    return Date.valueOf(time.toLocalDate());
  }
  
  @Override
  public int compareTo(TimeImpl obj) {
    return time.compareTo(obj.time);
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 5;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
  
}

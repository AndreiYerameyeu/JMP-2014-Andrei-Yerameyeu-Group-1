package com.epam.edp.util;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

import com.epam.edp.json.DateImplJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = DateImplJsonSerializer.class)
public class DateImpl implements Comparable<DateImpl>, Cloneable, Serializable {
  
  private static final long serialVersionUID = 163759174094789112L;
  
  private final LocalDate date;
  
  public DateImpl(java.sql.Date date) {
    this(date.toLocalDate());
  }
  
  public DateImpl(LocalDate date) {
    Assert.notNull(date);
    this.date = date;
  }
  
  public DateImpl(String date) {
    Assert.notNull(date);
    this.date = LocalDate.parse(date);
  }
  
  public static DateImpl getNow() {
    return new DateImpl(LocalDate.now());
  }
  
  public LocalDate getDate() {
    return date;
  }
  
  public java.util.Date toUtilDate() {
    return toSqlDate();
  }
  
  public java.sql.Date toSqlDate() {
    return Date.valueOf(date);
  }
  
  @Override
  public int compareTo(DateImpl obj) {
    return date.compareTo(obj.date);
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 7;
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

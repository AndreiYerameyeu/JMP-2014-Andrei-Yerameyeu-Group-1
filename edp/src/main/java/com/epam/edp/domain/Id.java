package com.epam.edp.domain;

import java.io.Serializable;


public interface Id extends Serializable, Comparable<Id> {
  
  public String getObjectType();
  
  public String getValue();
  
  public long getNumericValue();
  
  public boolean isNumeric();
  
}

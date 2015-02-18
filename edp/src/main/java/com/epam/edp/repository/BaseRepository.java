package com.epam.edp.repository;

import com.epam.edp.domain.BaseEntity;
import com.epam.edp.domain.Id;


public interface BaseRepository<T extends BaseEntity<?>> {
  
  public String getType();
  
  public T getById(Id id);
  
  public T getById(String id);
  
  public T getById(Object id);
  
  public Id parseId(Object value);
  
  public Id parseId(String value);
  
  public Id parseId(long value);
  
  public String getDefaultObjectFindSql();
  
  public String getUpdateSql(T object);
  
  public String getInsertSql(T object);
  
  public String getDeleteSql(T object);
  
}

package com.epam.edp.service;

import java.util.List;

import com.epam.edp.domain.BaseEntity;
import com.epam.edp.domain.Id;
import com.epam.edp.repository.BaseRepository;


public interface BaseService<T extends BaseEntity<?>, R extends BaseRepository<T>> {
  
  public <S extends T> List<S> save(Iterable<S> entities);
  
  public <S extends T> S save(S entity);
  
  public T find(Id id);
  
  public void delete(Id id);
  
  public void delete(T entity);
  
  public void delete(Iterable<? extends T> entities);
  
  public void deleteAll();
  
}

package com.epam.edp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.edp.domain.BaseEntity;
import com.epam.edp.domain.Id;
import com.epam.edp.repository.BaseRepository;


public class BaseService_Impl<T extends BaseEntity<?>, R extends BaseRepository<T>> implements BaseService<T, R> {
  
  @Autowired
  protected R repository;
  
  @Override
  public <S extends T> List<S> save(Iterable<S> entities) {
    return null;
  }
  
  @Override
  public <S extends T> S save(S entity) {
    return null;
  }
  
  @Override
  public T find(Id id) {
    return repository.getById(id);
  }
  
  @Override
  public void delete(Id id) {
    
  }
  
  @Override
  public void delete(T entity) {
    
  }
  
  @Override
  public void delete(Iterable<? extends T> entities) {
    
  }
  
  @Override
  public void deleteAll() {
    
  }
  
}

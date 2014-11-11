package com.epam.service;


import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.epam.domain.BaseEntity;
import com.epam.repository.BaseEntityRepository;

public interface BaseEntityService<T extends BaseEntity<ID>, ID extends Serializable, T_REPOSITIRY extends BaseEntityRepository<T, ID>> {
    
    public List<T> findAll();
    
    public List<T> findAll(Sort sort);
    
    public List<T> findAll(Iterable<ID> ids);
    
    public <S extends T> List<S> save(Iterable<S> entities);
    
    public void flush();
    
    public T saveAndFlush(T entity);
    
    public void deleteInBatch(Iterable<T> entities);
    
    public void deleteAllInBatch();
    
    public Page<T> findAll(Pageable pageable);
    
    public <S extends T> S save(S entity);
    
    public T findOne(ID id);
    
    public boolean exists(ID id);
    
    public long count();
    
    public void delete(ID id);
    
    public void delete(T entity);
    
    public void delete(Iterable<? extends T> entities);
    
    public void deleteAll();
}

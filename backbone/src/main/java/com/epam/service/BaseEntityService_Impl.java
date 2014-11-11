package com.epam.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.epam.domain.BaseEntity;
import com.epam.repository.BaseEntityRepository;

@Transactional
public abstract class BaseEntityService_Impl<T extends BaseEntity<ID>, ID extends Serializable, T_REPOSITORY extends BaseEntityRepository<T, ID>> implements BaseEntityService<T, ID, T_REPOSITORY> {
    
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Autowired
    protected T_REPOSITORY repository;
    
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }
    
    @Override
    public List<T> findAll(Iterable<ID> ids) {
        return repository.findAll(ids);
    }
    
    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return repository.save(entities);
    }
    
    @Override
    public void flush() {
        repository.flush();
    }
    
    @Override
    public T saveAndFlush(T entity) {
        return repository.saveAndFlush(entity);
    }
    
    @Override
    public void deleteInBatch(Iterable<T> entities) {
        repository.deleteInBatch(entities);
    }
    
    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }
    
    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    @Override
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }
    
    @Override
    public T findOne(ID id) {
        return repository.findOne(id);
    }
    
    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }
    
    @Override
    public long count() {
        return repository.count();
    }
    
    @Override
    public void delete(ID id) {
        repository.delete(id);
    }
    
    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }
    
    @Override
    public void delete(Iterable<? extends T> entities) {
        repository.delete(entities);
    }
    
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
    
    protected boolean needDebugExecutionTime() {
        return LOG.isDebugEnabled();
    }
    
    protected void debugExecutionTime(String methodName, long result) {
        LOG.debug("Method " + getClass().getCanonicalName() + "." + methodName + " executed for " + result + " milliseconds.");
    }
}

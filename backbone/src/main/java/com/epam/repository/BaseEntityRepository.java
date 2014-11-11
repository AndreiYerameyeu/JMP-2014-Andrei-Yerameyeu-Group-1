package com.epam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.domain.BaseEntity;

@Repository
public interface BaseEntityRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
    
}

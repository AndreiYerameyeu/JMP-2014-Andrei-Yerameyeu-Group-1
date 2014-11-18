package com.epam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.domain.Bookmark;

@Repository
public interface BookmarkRepository extends BaseEntityRepository<Bookmark, Long> {
    
    public final String FIND_ALL_QUERY_JOIN = "select b from Bookmark b LEFT join fetch b.tags";
    
    @Override
    @Query(value = BookmarkRepository.FIND_ALL_QUERY_JOIN)
    public List<Bookmark> findAll();
    
}

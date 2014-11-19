package com.epam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.domain.Bookmark;

@Repository
public interface BookmarkRepository extends BaseEntityRepository<Bookmark, Long> {
    
    public final String FIND_ALL_QUERY_JOIN = "select b from Bookmark b LEFT join fetch b.tags";
    public final String FIND_BY_ID_QUERY_JOIN = "select b from Bookmark b LEFT join fetch b.tags where b.id=?1";
    
    @Override
    @Query(value = BookmarkRepository.FIND_ALL_QUERY_JOIN)
    public List<Bookmark> findAll();
    
    @Override
    @Query(value = BookmarkRepository.FIND_BY_ID_QUERY_JOIN)
    public Bookmark findOne(Long arg0);
}

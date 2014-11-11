package com.epam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.domain.Tag;

@Repository
public interface TagRepository extends BaseEntityRepository<Tag, Long> {
    
    public final String FIND_ALL_QUERY_JOIN = "select t from Tag t LEFT join fetch t.bookmark bookmark";
    public final String FIND_BY_ID_QUERY_JOIN = "select t from Tag t LEFT join fetch t.bookmark bookmark where t.id=?1";
    public final String FIND_BY_BOOKMARK_ID_QUERY_JOIN = "select t from Tag t LEFT join fetch t.bookmark bookmark where bookmark.id=?1";
    
    @Override
    @Query(value = TagRepository.FIND_ALL_QUERY_JOIN)
    public List<Tag> findAll();
    
    @Query(value = TagRepository.FIND_BY_BOOKMARK_ID_QUERY_JOIN)
    public List<Tag> findAllByBookmark(Long bookmarkId);
    
    @Override
    @Query(value = TagRepository.FIND_BY_ID_QUERY_JOIN)
    public Tag findOne(Long arg);
    
}

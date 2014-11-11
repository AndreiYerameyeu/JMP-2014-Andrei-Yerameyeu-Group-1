package com.epam.repository;


import org.springframework.stereotype.Repository;

import com.epam.domain.Bookmark;

@Repository
public interface BookmarkRepository extends BaseEntityRepository<Bookmark, Long> {
    
}

package com.epam.service;

import com.epam.domain.Bookmark;
import com.epam.repository.BookmarkRepository;

public interface BookmarkService extends BaseEntityService<Bookmark, Long, BookmarkRepository> {
    
    public Bookmark save(String title, String url, String[] tags);
    
    public Bookmark save(Long id, String title, String url, String[] tags);
    
}

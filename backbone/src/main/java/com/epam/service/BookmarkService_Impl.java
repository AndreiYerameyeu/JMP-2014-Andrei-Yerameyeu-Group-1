package com.epam.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.domain.Bookmark;
import com.epam.repository.BookmarkRepository;

@Service("bookmarkService")
@Transactional
public class BookmarkService_Impl extends BaseEntityService_Impl<Bookmark, Long, BookmarkRepository> implements BookmarkService {
    
}

package com.epam.service;

import java.util.List;

import com.epam.domain.Tag;
import com.epam.repository.TagRepository;

public interface TagService extends BaseEntityService<Tag, Long, TagRepository> {
    
    public List<Tag> findAllByBookmark(Long bookmarkId);
}

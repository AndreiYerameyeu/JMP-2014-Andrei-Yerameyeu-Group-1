package com.epam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.domain.Tag;
import com.epam.repository.TagRepository;

@Service("tagService")
@Transactional
public class TagService_Impl extends BaseEntityService_Impl<Tag, Long, TagRepository> implements TagService {
    
    @Override
    public List<Tag> findAllByBookmark(Long bookmarkId) {
        return repository.findAllByBookmark(bookmarkId);
    }
    
}

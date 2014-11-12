package com.epam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.domain.Bookmark;
import com.epam.domain.Tag;
import com.epam.repository.BookmarkRepository;

@Service("bookmarkService")
@Transactional
public class BookmarkService_Impl extends BaseEntityService_Impl<Bookmark, Long, BookmarkRepository> implements BookmarkService {
    
    @Autowired
    protected TagService tagService;
    
    @Override
    public Bookmark save(Long id, String title, String url, String[] tags) {
        Bookmark toSave = findOne(id);
        if (null != title) {
            toSave.setTitle(title);
        }
        if (null != url) {
            toSave.setUrl(url);
        }
        toSave = save(toSave);
        
        List<Tag> tagList = tagService.findAllByBookmark(toSave.getId());
        
        Map<String, Tag> tagsMap = new HashMap<String, Tag>();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            tagsMap.put(tag.getTag(), tag);
        }
        
        List<Tag> newTags = new ArrayList<Tag>();
        for (int i = 0; i < tags.length; i++) {
            String newTagValue = tags[i];
            Tag oldTag = tagsMap.remove(newTagValue);
            if (null == oldTag) {
                Tag newTag = new Tag();
                newTag.setBookmark(toSave);
                newTag.setTag(newTagValue);
                newTags.add(newTag);
            } else {
                newTags.add(oldTag);
            }
        }
        
        tagService.delete(tagsMap.values());
        tagService.save(newTags);
        
        return toSave;
    }
    
    @Override
    public Bookmark save(String title, String url, String[] tags) {
        Bookmark newBookmark = new Bookmark();
        newBookmark.setTitle(title);
        newBookmark.setUrl(url);
        newBookmark = save(newBookmark);
        
        List<Tag> newTags = new ArrayList<Tag>();
        for (int i = 0; i < tags.length; i++) {
            Tag newTag = new Tag();
            newTag.setTag(tags[i]);
            newTag.setBookmark(newBookmark);
            newTags.add(newTag);
        }
        tagService.save(newTags);
        return null;
    }
    
}

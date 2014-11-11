package com.epam.web.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.domain.Bookmark;
import com.epam.domain.Tag;
import com.epam.service.BookmarkService;
import com.epam.service.TagService;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    
    @Autowired
    protected BookmarkService bookmarkService;
    
    @Autowired
    protected TagService tagService;
    
    @RequestMapping(params = { "id" }, method = { RequestMethod.GET })
    public Bookmark getBookmarkById(@RequestParam(value = "id", required = true) String id) {
        return bookmarkService.findOne(NumberUtils.toLong(id, 0));
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public List<Bookmark> getAllBookmarks(@RequestParam(value = "all", required = false) String all) {
        return bookmarkService.findAll();
    }
    
    @RequestMapping(params = { "id" }, method = { RequestMethod.POST, RequestMethod.PUT })
    public Bookmark saveBookmark(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "url", required = false) String url, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "tags", required = false) String tags) {
        Long idValue = NumberUtils.toLong(id, 0);
        if (idValue < 1) {
            return null;
        }
        Bookmark toSave = bookmarkService.findOne(idValue);
        if (null != title) {
            toSave.setTitle(title);
        }
        if (null != url) {
            toSave.setUrl(url);
        }
        
        toSave = bookmarkService.save(toSave);
        
        String[] tagsArray = StringUtils.split(tags, ',');
        List<Tag> tagList = tagService.findAllByBookmark(toSave.getId());
        
        Map<String, Tag> tagsMap = new HashMap<String, Tag>();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            tagsMap.put(tag.getTag(), tag);
        }
        
        List<Tag> newTags = new ArrayList<Tag>();
        for (int i = 0; i < tagsArray.length; i++) {
            String newTagValue = tagsArray[i];
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
    
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public Bookmark createBookmark(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "url", required = true) String url, @RequestParam(value = "title", required = true) String title, @RequestParam(value = "tags", required = true) String tags) {
        if (null != id) {
            return saveBookmark(id, url, title, tags);
        }
        Bookmark newBookmark = new Bookmark();
        newBookmark.setTitle(title);
        newBookmark.setUrl(url);
        newBookmark = bookmarkService.save(newBookmark);
        String[] tagsArray = StringUtils.split(tags, ',');
        List<Tag> newTags = new ArrayList<Tag>();
        for (int i = 0; i < tagsArray.length; i++) {
            Tag newTag = new Tag();
            newTag.setTag(tagsArray[i]);
            newTag.setBookmark(newBookmark);
            newTags.add(newTag);
        }
        tagService.save(newTags);
        return newBookmark;
    }
    
    @RequestMapping(method = { RequestMethod.DELETE })
    public void deleteBookmark(@RequestParam(value = "id", required = true) String id) {
        Long idValue = NumberUtils.toLong(id, 0);
        tagService.delete(idValue);
    }
    
}

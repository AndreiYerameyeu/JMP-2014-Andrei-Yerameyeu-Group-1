package com.epam.web.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.domain.Bookmark;
import com.epam.domain.Tag;
import com.epam.service.BookmarkService;
import com.epam.service.TagService;
import com.epam.web.view.model.BookmarkModel;
import com.epam.web.view.model.TagModel;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    
    @Autowired
    protected BookmarkService bookmarkService;
    
    @Autowired
    protected TagService tagService;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public Bookmark getBookmarkById(@PathVariable String id) {
        return bookmarkService.findOne(NumberUtils.toLong(id, 0));
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public List<BookmarkModel> getAllBookmarks() {
        List<BookmarkModel> bookmarkModels = new ArrayList<BookmarkModel>();
        
        List<Bookmark> allBookmarks = bookmarkService.findAll();
        for (Bookmark bookmark : allBookmarks) {
            BookmarkModel bookmarkModel = new BookmarkModel();
            bookmarkModel.setTitle(bookmark.getTitle());
            bookmarkModel.setUrl(bookmark.getUrl());
            List<Tag> bookmarkTags = tagService.findAllByBookmark(bookmark.getId());
            List<TagModel> tagModels = new ArrayList<TagModel>();
            for (Tag bookmarkTag : bookmarkTags) {
                TagModel tagModel = new TagModel();
                tagModel.setTag(bookmarkTag.getTag());
                tagModels.add(tagModel);
            }
            bookmarkModel.setTags(tagModels);
            bookmarkModels.add(bookmarkModel);
        }
        
        return bookmarkModels;
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public Bookmark saveBookmark(@PathVariable String id, @RequestParam(value = "url", required = false) String url, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "tags", required = false) String tags) {
        Long idValue = NumberUtils.toLong(id, 0);
        if (idValue < 1) {
            return null;
        }
        String[] tagsArray = StringUtils.split(tags, ',');
        Bookmark toSave = bookmarkService.save(idValue, title, url, tagsArray);
        return toSave;
    }
    
    @RequestMapping(method = { RequestMethod.POST })
    public Bookmark createBookmark(@RequestParam(value = "url", required = true) String url, @RequestParam(value = "title", required = true) String title, @RequestParam(value = "tags", required = true) String tags) {
        String[] tagsArray = StringUtils.split(tags, ',');
        Bookmark newBookmark = bookmarkService.save(title, url, tagsArray);
        
        return newBookmark;
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public void deleteBookmark(@PathVariable String id) {
        Long idValue = NumberUtils.toLong(id, 0);
        tagService.delete(idValue);
    }
    
}

package com.epam.web.rest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.domain.Bookmark;
import com.epam.domain.Tag;
import com.epam.service.TagService;
import com.epam.web.view.model.TagCountModel;

@RestController
@RequestMapping("/tag")
public class TagController extends AbstractRestController {
    
    @Autowired
    protected TagService tagService;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public Tag getTagById(HttpServletRequest request, @PathVariable String id) {
        return tagService.findOne(NumberUtils.toLong(id, 0));
    }
    
    @RequestMapping(params = { "bookmarkid" }, method = { RequestMethod.GET })
    public List<Tag> getTagByBookmarkId(HttpServletRequest request, @RequestParam(value = "bookmarkid", required = true) String bookmarkid) {
        Long bookmarkIdValue = NumberUtils.toLong(bookmarkid, -1L);
        if (bookmarkIdValue > 0) {
            return tagService.findAllByBookmark(bookmarkIdValue);
        } else {
            return Collections.emptyList();
        }
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public List<Tag> getAllTags(HttpServletRequest request) {
        return tagService.findAll();
    }
    
    @RequestMapping(value = { "/tagCount", "/tagcount" }, method = { RequestMethod.GET })
    public List<TagCountModel> getAllTagCount(HttpServletRequest request) {
        List<TagCountModel> tagCountModels = new ArrayList<TagCountModel>();
        
        Map<String, List<Bookmark>> counts = new HashMap<String, List<Bookmark>>();
        List<Tag> tags = tagService.findAll();
        
        for (Tag tag : tags) {
            Bookmark bookmark = tag.getBookmark();
            if (null != bookmark) {
                String key = tag.getTag();
                List<Bookmark> bookmarks = counts.get(key);
                if (null == bookmarks) {
                    bookmarks = new ArrayList<Bookmark>();
                }
                bookmarks.add(bookmark);
                counts.put(key, bookmarks);
            }
        }
        
        for (String key : counts.keySet()) {
            TagCountModel tagCountModel = new TagCountModel();
            tagCountModel.setTag(key);
            String link = request.getContextPath().concat("?filter=").concat(key);
            tagCountModel.setLink(link);
            List<Bookmark> bookmarks = counts.get(key);
            if (null != bookmarks) {
                tagCountModel.setCount(bookmarks.size());
            } else {
                tagCountModel.setCount(0);
            }
            tagCountModels.add(tagCountModel);
        }
        return tagCountModels;
    }
}

package com.epam.web.rest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.domain.Bookmark;
import com.epam.service.BookmarkService;
import com.epam.service.TagService;
import com.epam.web.view.binder.BookmarkModelBinder;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController extends AbstractRestController {
    
    @Autowired
    protected BookmarkService bookmarkService;
    
    @Autowired
    protected TagService tagService;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public Bookmark getBookmarkById(HttpServletRequest request, @PathVariable String id) {
        return bookmarkService.findOne(NumberUtils.toLong(id, 0));
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public List<Bookmark> getAllBookmarks(HttpServletRequest request) {
        return bookmarkService.findAll();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT }, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    Bookmark saveBookmark(HttpServletRequest request, @PathVariable String id, @RequestBody BookmarkModelBinder body, BindingResult results) {
        processRestRequest(body, results);
        Long idValue = NumberUtils.toLong(id, 0);
        if (idValue < 1) {
            return null;
        }
        String[] tagsArray = StringUtils.split(body.getTags(), ',');
        Bookmark toSave = bookmarkService.save(idValue, body.getTitle(), body.getUrl(), tagsArray);
        return toSave;
    }
    
    @RequestMapping(method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    Bookmark createBookmark(HttpServletRequest request, @RequestBody BookmarkModelBinder body, BindingResult results) {
        processRestRequest(body, results);
        String[] tagsArray = StringUtils.split(body.getTags(), ',');
        Bookmark newBookmark = bookmarkService.save(body.getTitle(), body.getUrl(), tagsArray);
        
        return newBookmark;
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE }, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void deleteBookmark(HttpServletRequest request, @PathVariable String id) {
        Long idValue = NumberUtils.toLong(id, 0);
        tagService.delete(idValue);
    }
    
}

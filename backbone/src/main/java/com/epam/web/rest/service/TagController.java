package com.epam.web.rest.service;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.domain.Tag;
import com.epam.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {
    
    @Autowired
    protected TagService tagService;
    
    @RequestMapping(params = { "id" }, method = { RequestMethod.GET })
    public Tag getTagById(@RequestParam(value = "id", required = true) String id) {
        return tagService.findOne(NumberUtils.toLong(id, 0));
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public List<Tag> getAllTags(@RequestParam(value = "all", required = false) String all) {
        return tagService.findAll();
    }
}

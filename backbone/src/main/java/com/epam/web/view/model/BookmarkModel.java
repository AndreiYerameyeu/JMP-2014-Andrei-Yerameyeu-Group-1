package com.epam.web.view.model;

import java.util.List;


public class BookmarkModel {
    
    private String url;
    private String title;
    private List<TagModel> tags;
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<TagModel> getTags() {
        return tags;
    }
    
    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }
    
}
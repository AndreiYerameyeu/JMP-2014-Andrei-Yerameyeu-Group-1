package com.epam.web.view.binder;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class BookmarkModelBinder {
    
    @NotEmpty(message = "Bookmark.Title.Empty")
    @Length(min = 3, max = 32, message = "Bookmark.Url.Size")
    private String title;
    
    @NotEmpty(message = "Bookmark.Url.Empty")
    @Length(min = 3, max = 32, message = "Bookmark.Url.Size")
    private String url;
    
    @NotEmpty(message = "Bookmark.Tags.Empty")
    @Length(min = 3, max = 128, message = "Bookmark.Tags.Size")
    private String tags;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"url\":");
        if (null != url) {
            sb.append('\"');
            sb.append(url);
            sb.append('\"');
        } else {
            sb.append("null");
        }
        sb.append(", \"title\":");
        if (null != title) {
            sb.append('\"');
            sb.append(title);
            sb.append('\"');
        } else {
            sb.append("null");
        }
        sb.append(", \"tags\":");
        if (null != tags) {
            sb.append('\"');
            sb.append(tags);
            sb.append('\"');
        } else {
            sb.append("null");
        }
        sb.append('}');
        return sb.toString();
    }
    
}

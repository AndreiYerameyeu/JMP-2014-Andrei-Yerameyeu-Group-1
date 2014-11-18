package com.epam.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bookmark")
public class Bookmark extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -7063021852492011952L;
    
    @Column(name = "url")
    private String url;
    
    @Column(name = "title")
    private String title;
    
    @OneToMany(mappedBy = "bookmark", fetch = FetchType.LAZY)
    private List<Tag> tags;
    
    public Bookmark() {
        
    }
    
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
    
    public List<Tag> getTags() {
        return tags;
    }
    
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    @Override
    public boolean equals(Object arg) {
        if (this == arg) {
            return true;
        }
        if (!(arg instanceof Bookmark)) {
            return false;
        }
        Bookmark that = (Bookmark)arg;
        if (null != url ? !url.equals(that.url) : that.url != null) {
            return false;
        }
        if (null != title ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hash = 17;
        int result = super.hashCode();
        result = hash * result + (null != url ? url.hashCode() : 0);
        result = hash * result + (null != title ? title.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" [url=");
        sb.append((null != url) ? url.toString() : "{null}");
        sb.append("], [title=");
        sb.append((null != title) ? title.toString() : "{null}");
        sb.append("]");
        return sb.toString();
    }
    
}

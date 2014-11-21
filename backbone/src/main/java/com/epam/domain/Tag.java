package com.epam.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tag")
public class Tag extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -7063021852492011952L;
    
    @Column(name = "tag")
    private String tag;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = (CascadeType.ALL))
    @JoinColumn(name = "id_bookmark")
    @BatchSize(size = 100)
    @JsonIgnore
    private Bookmark bookmark;
    
    public Tag() {
        
    }
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public Bookmark getBookmark() {
        return bookmark;
    }
    
    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }
    
    @Override
    public boolean equals(Object arg) {
        if (this == arg) {
            return true;
        }
        if (!(arg instanceof Tag)) {
            return false;
        }
        Tag that = (Tag)arg;
        if (null != tag ? !tag.equals(that.tag) : that.tag != null) {
            return false;
        }
        if (null != bookmark ? !bookmark.equals(that.bookmark) : that.bookmark != null) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hash = 23;
        int result = super.hashCode();
        result = hash * result + (null != tag ? tag.hashCode() : 0);
        result = hash * result + (null != bookmark ? bookmark.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" [tag=");
        sb.append((null != tag) ? tag.toString() : "{null}");
        sb.append("], [bookmark=");
        sb.append((null != bookmark) ? bookmark.toString() : "{null}");
        sb.append("]");
        return sb.toString();
    }
    
}

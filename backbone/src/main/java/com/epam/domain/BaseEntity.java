package com.epam.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    
    private static final long serialVersionUID = -2972989275646002005L;
    
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;
    
    public ID getId() {
        return id;
    }
    
    public void setId(ID id) {
        this.id = id;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object arg) {
        if (this == arg) {
            return true;
        }
        if (!(arg instanceof BaseEntity)) {
            return false;
        }
        BaseEntity<ID> that = (BaseEntity<ID>)arg;
        if (null != id ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hash = 251;
        int result = id != null ? id.hashCode() : 0;
        result = hash * result;
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append(": ");
        sb.append("[id=");
        sb.append(id);
        sb.append("]");
        return sb.toString();
    }
}

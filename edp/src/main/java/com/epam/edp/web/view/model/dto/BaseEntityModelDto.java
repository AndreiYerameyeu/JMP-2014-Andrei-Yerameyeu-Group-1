package com.epam.edp.web.view.model.dto;

import com.epam.edp.domain.BaseEntity;
import com.epam.edp.domain.Id;

public abstract class BaseEntityModelDto<T extends BaseEntity<ID>, ID extends Id> {
  
  private ID id;
  
  public BaseEntityModelDto() {
  }
  
  public BaseEntityModelDto(T obj) {
    setId(obj.getId());
  }
  
  public ID getId() {
    return this.id;
  }
  
  public void setId(ID id) {
    this.id = id;
  }
  
}

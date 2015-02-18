package com.epam.edp.web.view.model.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.epam.edp.domain.NumericId;
import com.epam.edp.domain.ticket.Ticket;


public class TicketModelDto extends BaseEntityModelDto<Ticket, NumericId> {
  
  private int row;
  private int position;
  
  public TicketModelDto() {
    super();
  }
  
  public TicketModelDto(Ticket ticket) {
    super(ticket);
    setId(ticket.getId());
    setRow(ticket.getRow());
    setPosition(ticket.getPosition());
  }
  
  public int getRow() {
    return row;
  }
  
  public void setRow(int row) {
    this.row = row;
  }
  
  public int getPosition() {
    return position;
  }
  
  public void setPosition(int position) {
    this.position = position;
  }
  
  @Override
  public boolean equals(Object arg) {
    return EqualsBuilder.reflectionEquals(this, arg);
  }
  
  @Override
  public int hashCode() {
    final int hash = 701;
    return HashCodeBuilder.reflectionHashCode(7, hash, this, false, null);
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}

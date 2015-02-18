package com.epam.edp.repository.ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.epam.edp.domain.NumericId;
import com.epam.edp.domain.ticket.Ticket;
import com.epam.edp.repository.BaseRepository_Impl;

@Component
public class TicketRepository_Impl extends BaseRepository_Impl<Ticket> implements TicketRepository {
  
  @Autowired
  private TicketRepository_Impl(DataSource dataSource) {
    super(dataSource, "TICKET");
  }
  
  @Override
  public RowMapper<Ticket> getRowMapper() {
    return new RowMapper<Ticket>() {
      
      @Override
      public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        NumericId id = new NumericId(getType(), rs.getLong("Id"));
        ticket.setId(id);
        return ticket;
      }
      
    };
  }
  
  @Override
  public PreparedStatementSetter getPreparedStatementSetter(final Ticket ticket) {
    return new PreparedStatementSetter() {
      
      @Override
      public void setValues(PreparedStatement ps) throws SQLException {
        int i = 0;
        ps.setLong(++i, ticket.getId().getNumericValue());
      }
      
    };
  }
  
  @Override
  public List<Ticket> getAllEntities() {
    return super.getAllEntities();
  }
  
  @Override
  protected List<String> getDefaultSelectFields() {
    List<String> fields = super.getDefaultSelectFields();
    fields.add("Name");
    return fields;
  }
  
  @Override
  protected String getTableName() {
    return "tblTicket";
  }
  
  
  
}

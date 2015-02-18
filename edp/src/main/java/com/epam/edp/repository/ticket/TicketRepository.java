package com.epam.edp.repository.ticket;

import java.util.List;

import com.epam.edp.domain.ticket.Ticket;
import com.epam.edp.repository.BaseRepository;


public interface TicketRepository extends BaseRepository<Ticket> {
  
  public List<Ticket> getAllEntities();
  
}

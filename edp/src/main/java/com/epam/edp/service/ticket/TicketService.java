package com.epam.edp.service.ticket;

import java.util.List;

import com.epam.edp.domain.ticket.Ticket;
import com.epam.edp.repository.ticket.TicketRepository;
import com.epam.edp.service.BaseService;


public interface TicketService extends BaseService<Ticket, TicketRepository> {
  
  public List<Ticket> getAllTickets();
  
}

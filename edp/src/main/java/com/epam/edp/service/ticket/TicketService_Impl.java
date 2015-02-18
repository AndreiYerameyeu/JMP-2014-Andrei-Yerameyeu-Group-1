package com.epam.edp.service.ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import com.epam.edp.domain.ticket.Ticket;
import com.epam.edp.exception.BaseRuntimeException;
import com.epam.edp.repository.ticket.TicketRepository;
import com.epam.edp.service.BaseService_Impl;


@Service("ticketService")
public class TicketService_Impl extends BaseService_Impl<Ticket, TicketRepository> implements TicketService {
  
  private final TransactionTemplate transactionTemplate;
  
  @Autowired
  public TicketService_Impl(PlatformTransactionManager transactionManager) {
    Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
    transactionTemplate = new TransactionTemplate(transactionManager);
  }
  
  @Override
  public Ticket save(Ticket t) {
    Integer result = transactionTemplate.execute(new TransactionCallback<Integer>() {
      
      @Override
      public Integer doInTransaction(TransactionStatus status) {
        try {
          return Integer.valueOf(1);
        } catch (BaseRuntimeException ex) {
          status.setRollbackOnly();
        }
        return Integer.valueOf(0);
      }
      
    });
    
    if (result.intValue() > 0) {
      
    }
    
    return null;
  }
  
  @Override
  public List<Ticket> getAllTickets() {
    return repository.getAllEntities();
  }
  
}

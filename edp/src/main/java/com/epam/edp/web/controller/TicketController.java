package com.epam.edp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.edp.domain.ticket.Ticket;
import com.epam.edp.service.ticket.TicketService;
import com.epam.edp.web.view.model.dto.TicketModelDto;

@Controller
@RequestMapping("/tickets")
public class TicketController {
  
  @Autowired
  private TicketService ticketService;
  
  @RequestMapping(method = { RequestMethod.GET })
  @ResponseBody
  public ResponseEntity<List<TicketModelDto>> getAllTickets(HttpServletRequest request) {
    List<Ticket> ticketList = ticketService.getAllTickets();
    List<TicketModelDto> dtoList = new ArrayList<TicketModelDto>(ticketList.size());
    for (Ticket ticket : ticketList) {
      dtoList.add(new TicketModelDto(ticket));
    }
    return new ResponseEntity<List<TicketModelDto>>(dtoList, HttpStatus.OK);
  }
  
}

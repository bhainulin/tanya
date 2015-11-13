package com.epam.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.beans.Ticket;
import com.epam.sc.repository.EventAuditoriumRepository;
import com.epam.sc.repository.TicketRepository;
import com.epam.sc.services.api.TicketService;

@Service
public class TicketServiceImpl implements TicketService{
  
  @Autowired
  // @Qualifier("ticketMapRepository")
  @Qualifier("jdbcTicketDao")
  private TicketRepository ticketDao;
  
  @Autowired
  // @Qualifier("eventAuditoriumMapRepository")
  @Qualifier("jdbcEventAuditoriumDao")
  private EventAuditoriumRepository eventAudDao;
  
  public List<Ticket> getAll(){
    return ticketDao.getAll();
  }
  
  public void updateTicket(Ticket t){
    ticketDao.updateTicket(t);
  }
  
  public void createTickets(EventAuditorium eventAuditorium){
    Event event =  eventAuditorium.getEvent();
    Auditorium auditorium = eventAuditorium.getAuditorium();
    List<Integer> vipSeats = auditorium.getVipSeats();
    Double ticketBasePrice = event.getTicketBasePrice();
    
    int numberOfTickets = auditorium.getCountOfSeats();
    for(int seat = 1; seat <= numberOfTickets; seat++){
      Double price = vipSeats.contains(seat) ? ticketBasePrice*2 : ticketBasePrice;
      Ticket ticket = new Ticket(eventAuditorium, seat, price, true);
      ticketDao.createTicket(ticket);
    }
  }
  
  public void removeTicket(Long id){
    ticketDao.removeTicket(id);
  }

}

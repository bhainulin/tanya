package com.epam.sc.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;
import com.epam.sc.repository.UserTicketRepository;
import com.epam.sc.services.api.BookingService;
import com.epam.sc.services.api.DiscountService;
import com.epam.sc.services.api.SomeTestAnnotation;
import com.epam.sc.services.api.TicketService;

@Service
public class BookingServiceImpl implements BookingService{
  
  @Autowired
  private UserTicketRepository userTicketRepository;
  
  @Autowired
  private TicketService ticketService;
  
  @Autowired
  private DiscountService discountService;
  
  
  public Map<Ticket, Double> getTicketPrice(Event event, Auditorium auditorium, LocalDateTime time, List<Integer> seats, User user){
    List<Ticket> tiketsForEvent = getTickets(event, auditorium, time, true);
    Map<Ticket, Double> priceMap = new HashMap<Ticket, Double>();
    for(Ticket t : tiketsForEvent){
      Integer seat = t.getSeat();
      if(seats.contains(seat)){
        priceMap.put(t, discountService.getPriceAfterDiscount(t, user));
      }
    }
    return priceMap;
  }
  
  public List<Ticket> getTickets(Event event, Auditorium auditorium, LocalDateTime time, boolean showOnlyFree){
    List<Ticket> all = ticketService.getAll();
    List<Ticket> result = new ArrayList<Ticket>(); 
    for(Ticket t : all){
      EventAuditorium eventAuditorium = t.getEventAuditorium();
      if (eventAuditorium.getEvent().equals(event) && eventAuditorium.getAuditorium().equals(auditorium) && eventAuditorium.getTime().equals(time)) {
        if(showOnlyFree){
          if(!t.isFree()){
            continue;
          }
        }
        result.add(t);
      }
    }
    return result;
  }
  
  @SomeTestAnnotation(str = "booking for register user")
  public UserTicket bookTicket(Ticket ticket, User user, Double finalPrice){
    UserTicket userTicket = new UserTicket(user, ticket, finalPrice);
    userTicketRepository.createUserTicket(userTicket);
    ticket.setFree(false);
    ticketService.updateTicket(ticket);
    return userTicket;
  }
  
  @SomeTestAnnotation(str = "booking for unRegister user")
  public UserTicket bookTicket(Ticket ticket, Double finalPrice){
    UserTicket userTicket = new UserTicket(null, ticket, finalPrice);
    userTicketRepository.createUserTicket(userTicket);
    ticket.setFree(false);
    ticketService.updateTicket(ticket);
    return userTicket;
  }

}

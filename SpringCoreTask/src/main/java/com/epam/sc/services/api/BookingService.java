package com.epam.sc.services.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;

/**
 * 
 * Provides API for manipulating booking process.
 *
 */
public interface BookingService {
  /**
   * 
   * @param event
   * @param auditorium
   * @param time
   * @param seats
   * @param user
   * @return
   */
  public Map<Ticket, Double> getTicketPrice(Event event, Auditorium auditorium, LocalDateTime time, List<Integer> seats, User user);
  
  /**
   * 
   * @param event
   * @param auditorium
   * @param time
   * @param showOnlyFree
   * @return
   */
  public List<Ticket> getTickets(Event event, Auditorium auditorium, LocalDateTime time, boolean showOnlyFree);
  
  /**
   * 
   * @param ticket
   * @param user
   * @param finalPrice
   */
  public UserTicket bookTicket(Ticket ticket, User user, Double finalPrice);
  
  /**
   * 
   * @param ticket
   * @param finalPrice
   */
  public UserTicket bookTicket(Ticket ticket, Double finalPrice);
}

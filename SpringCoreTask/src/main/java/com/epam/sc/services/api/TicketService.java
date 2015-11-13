package com.epam.sc.services.api;

import java.util.List;

import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.beans.Ticket;

/**
 * 
 * Provides API for manipulating tickets.
 *
 */
public interface TicketService {
  /**
   * 
   * @return
   */
  public List<Ticket> getAll();
  
  /**
   * 
   * @param t
   */
  public void updateTicket(Ticket t);
  
  /**
   * 
   * @param eventAuditorium
   */
  public void createTickets(EventAuditorium eventAuditorium);
  
  /**
   * 
   * @param id
   */
  public void removeTicket(Long id);

}

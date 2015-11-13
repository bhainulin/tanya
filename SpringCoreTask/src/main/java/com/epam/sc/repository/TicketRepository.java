package com.epam.sc.repository;

import java.util.List;

import com.epam.sc.beans.Ticket;

/**
 * Provides repository for manipulating tickets.
 *
 */
public interface TicketRepository {
  /**
   * get all tickets
   * @return
   */
  public List<Ticket> getAll();
  
  /**
   * create ticket
   * @param ev
   * @return
   */
  public Long createTicket(Ticket ev);
  
  /**
   * remove ticket
   * @param id
   */
  public void removeTicket(Long id);
  
  /**
   * update ticket
   * @param t
   */
  public void updateTicket(Ticket t);

}

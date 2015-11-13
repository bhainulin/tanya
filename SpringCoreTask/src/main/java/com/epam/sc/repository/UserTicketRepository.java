package com.epam.sc.repository;

import java.util.List;

import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;

/**
 *  Provides repository for manipulating user's tickets.
 *
 */
public interface UserTicketRepository {
  /**
   * get all user's tickets
   * @return
   */
  public List<UserTicket> getAll();
  
  /**
   * create user ticket
   * @param ev
   * @return
   */
  public Long createUserTicket(UserTicket ev);
 
  /**
   * remove user ticket
   * @param id
   */
  public void removeUserTicket(Long id);
  
  /**
   * get tickets for special user
   * @param u
   * @return
   */
  public List<UserTicket> getUserTicket(User u);

}

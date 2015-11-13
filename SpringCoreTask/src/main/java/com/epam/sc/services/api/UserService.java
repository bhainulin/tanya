package com.epam.sc.services.api;

import java.util.List;

import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;

/**
 * Provides API for manipulating users.
 *
 */
public interface UserService {
  /**
   * get all users
   * @return
   */
  public List<User> getAllUsers();
  
  /**
   * get lucky users
   * @return
   */
  public List<User> getLuckyUsers();
  
  /**
   * create user
   * @param user
   */
  public Long registerUser(User user);
  
  /**
   * 
   * @param user
   */
  public void updateUser(User user);
  
  /**
   * remove user
   * @param id
   */
  public void removeUser(Long id);
  
  /**
   * remove user
   * @param user
   */
  public void removeUser(User user);
  
  /**
   * get user
   * @param id
   * @return
   */
  public User getUserById(Long id);
  
  /**
   * get user
   * @param name
   * @return
   */
  public User getUserByName(String name);
  
  /**
   * get user
   * @param email
   * @return
   */
  public User getUserByEmail(String email);
  
  /**
   * get user
   * @param user
   * @return
   */
  public List<UserTicket> getBookedTickets(User user);

}

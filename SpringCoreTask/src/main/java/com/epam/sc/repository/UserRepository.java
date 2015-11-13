package com.epam.sc.repository;

import java.util.List;

import com.epam.sc.beans.User;

/**
 * Provides repository for manipulating users.
 *
 */
public interface UserRepository {
  
  /**
   * Get all users
   * @return
   */
  public List<User> getAll();
  
  /**
   * get lucky users (systemMessage != null)
   * @return
   */
  public List<User> getLuckyUsers();
  
  /**
   * create user
   * @param user
   * @return id
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
  
}

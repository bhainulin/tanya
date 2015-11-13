package com.epam.sc.repository;

import java.util.List;

import com.epam.sc.beans.Event;

/**
 * Provides repository for manipulating films(events).
 *
 */
public interface EventRepository {
  /**
   * get all films
   * @return
   */
  public List<Event> getAll();
  
  /**
   * create film
   * @param ev
   * @return
   */
  public Long createEvent(Event ev);
  
  /**
   * remove film
   * @param id
   */
  public void removeEvent(Long id);
  
  /**
   * get film
   * @param name
   * @return
   */
  public Event getEventByName(String name);
  
  public Event getEventById(Long id);

}

package com.epam.sc.services.api;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.EventAuditorium;

/**
 * 
 * Provides API for manipulating films.
 *
 */
public interface EventService {
  /**
   * 
   * @return
   */
  public List<Event> getAll();
  
  /**
   * 
   * @param ev
   * @return
   */
  public Long createEvent(Event ev);
  
  /**
   * 
   * @param id
   */
  public void removeEvent(Long id);
  
  /**
   * 
   * @param name
   * @return
   */
  public Event getEventByName(String name);
  
  public Event getEventById(Long id);
  
  /**
   * 
   * @param event
   * @param auditorium
   * @param time
   */
  public void assignAuditorium(Event event, Auditorium auditorium, LocalDateTime time);
  
  /**
   * 
   * @return
   */
  public List<EventAuditorium> getAllEventAuditorium();
  
  /**
   * 
   * @param event
   * @param time
   * @return
   */
  public EventAuditorium getEventAuditorium(Event event, LocalDateTime time);

}

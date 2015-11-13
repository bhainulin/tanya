package com.epam.sc.repository;

import java.util.List;

import com.epam.sc.beans.EventAuditorium;

/**
 * Provides repository for manipulating films in special auditorium and time.
 *
 */
public interface EventAuditoriumRepository {
  
  /**
   * get all films
   * @return
   */
  List<EventAuditorium> getAll();
  
  EventAuditorium getEventAuditorium(Long id);
  
  /**
   * create film in special auditorium and time
   * @param ev
   * @return
   */
  Long createEventAud(EventAuditorium ev);
  
  /**
   * remove films
   * @param id
   */
  void removeEventAud(Long id);

}

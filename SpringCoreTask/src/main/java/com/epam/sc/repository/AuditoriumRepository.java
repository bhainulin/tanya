package com.epam.sc.repository;

import java.util.List;

import com.epam.sc.beans.Auditorium;

/**
 * Provides repository for manipulating Auditoriums.
 *
 */
public interface AuditoriumRepository {
  
  /**
   * get all Auditoriums
   * @return
   */
  List<Auditorium> getAuditoriums();
  
  Auditorium getAuditoriumById(Long id);
}

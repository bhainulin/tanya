package com.epam.sc.services.api;

import java.util.List;

import com.epam.sc.beans.Auditorium;

/**
 * 
 * Provides API for manipulating Auditoriums
 *
 */
public interface AuditoriumService {
  /**
   * get all Auditoriums
   * @return
   */
  public List<Auditorium> getAuditoriums();
  
  /**
   * get seat
   * @param name of auditorium
   * @return
   */
  public Integer getSeatsNumber(String name);
  
  /**
   * get vip seats
   * @param name
   * @return
   */
  public List<Integer> getVipSeats(String name);
  
  /**
   * get auditorium
   * @param name
   * @return
   */
  public Auditorium getAuditoriumByName(String name);
  
  public Auditorium getAuditoriumById(Long id);

}

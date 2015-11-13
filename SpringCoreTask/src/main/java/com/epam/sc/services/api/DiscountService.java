package com.epam.sc.services.api;

import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;

/**
 * Provides API for manipulating Discounts.
 *
 */
public interface DiscountService {
  /**
   * 
   * @param ticket
   * @param user
   * @return
   */
  public Double getPriceAfterDiscount(Ticket ticket, User user);

}

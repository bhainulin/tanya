package com.epam.sc.beans.discount;

import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;

public abstract class DiscountStrategy {
  
  private Integer discount;
  protected String name = "UNKNOWN";
 
  public DiscountStrategy() {
  }

  public DiscountStrategy(Integer discount) {
    this.discount = discount;
  }
  
  public Integer getDiscount() {
    return discount;
  }

  public void setDiscount(Integer discount) {
    this.discount = discount;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  /**
   * check and calculate Discount
   * @param ticket
   * @param user
   * @param existingDiscount
   * @return new discount if it is more then existingDiscount
   */
  public abstract Integer calculateDiscount(Ticket ticket, User user, Integer existingDiscount);

  @Override
  public String toString() {
    return "DiscountStrategy [discount=" + discount + ", name=" + name + "]";
  }
}

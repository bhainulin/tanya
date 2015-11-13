package com.epam.sc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;
import com.epam.sc.beans.discount.DiscountStrategy;
import com.epam.sc.services.api.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
  
  private List<DiscountStrategy> list;
  
  public DiscountServiceImpl(List<DiscountStrategy> list) {
    this.list = list;
  }
  
  public List<DiscountStrategy> getAllDiscountStrategy(){
    return list;
  }
  
  public Double getPriceAfterDiscount(Ticket ticket, User user){
     Double initPrice = ticket.getPrice();
     Double resultPrice = initPrice;
     Integer discount = 0;     
     for(DiscountStrategy strategy : list){
       discount = strategy.calculateDiscount(ticket, user, discount);
     }
     resultPrice = initPrice * (1-(double)discount/100);
     return resultPrice;
  }
  
}

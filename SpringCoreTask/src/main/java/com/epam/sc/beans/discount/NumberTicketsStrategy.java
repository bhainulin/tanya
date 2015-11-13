package com.epam.sc.beans.discount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;
import com.epam.sc.repository.UserTicketRepository;

@Component
public class NumberTicketsStrategy  extends DiscountStrategy{
  
  private Integer count = 10;
  
  @Autowired
  private UserTicketRepository userTicketRepository;

  public NumberTicketsStrategy() {   
    super.name = "NumberTickets"; 
  }

  public NumberTicketsStrategy(Integer discount, Integer count) {
    super(discount);
    if(count > 0){
      this.count = count;
    }
  }

  @Override
  public Integer calculateDiscount(Ticket ticket, User user, Integer existingDiscount) {
    Integer result = existingDiscount;
    Integer strategyDiscount = super.getDiscount();
    boolean userHaveTicketHistory = false;
    int numberOfAllTickets = 0;
    List<UserTicket> userTicket = userTicketRepository.getUserTicket(user);
    if(userTicket != null){
      numberOfAllTickets = userTicket.size();
      userHaveTicketHistory = numberOfAllTickets > 0 ? true : false;
    }
    if(userHaveTicketHistory && ((numberOfAllTickets+1) % this.count == 0)){
      result = (strategyDiscount > existingDiscount) ? strategyDiscount : existingDiscount;
    }
    
    return result;
  }

}

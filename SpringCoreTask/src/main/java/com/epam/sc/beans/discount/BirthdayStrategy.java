package com.epam.sc.beans.discount;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;

@Component
public class BirthdayStrategy extends DiscountStrategy{

  public BirthdayStrategy() {
    super();
    super.name = "Birthday";  
  }

  @Override
  public Integer calculateDiscount(Ticket ticket, User user, Integer existingDiscount) {
    Integer result = existingDiscount;
    Integer strategyDiscount = super.getDiscount();
    LocalDate birthday = user.getBirthday();
    LocalDateTime ticketTime = ticket.getEventAuditorium().getTime();
    if(birthday != null){
      if(birthday.getMonth().equals(ticketTime.getMonth()) && birthday.getDayOfMonth() == ticketTime.getDayOfMonth()){
        result = (strategyDiscount > existingDiscount) ? strategyDiscount : existingDiscount;
      }
    }
    return result;
  }
}

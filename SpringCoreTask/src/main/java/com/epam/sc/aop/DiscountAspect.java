package com.epam.sc.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.sc.beans.Identifiable;
import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;
import com.epam.sc.beans.discount.DiscountStrategy;
import com.epam.sc.services.api.CounterService;

@Aspect
@Component
public class DiscountAspect{
  
  @Autowired
  private CounterService<Identifiable> counterService;
  
  /**
   * How many times each discount was given total and for specific user
   * Map<Event, Integer>
   */
  @After(value = "args(ticket, user, existingDiscount) && execution(* com.epam.sc.beans.discount.DiscountStrategy+.calculateDiscount(..))", 
         argNames = "ticket, user, existingDiscount")
  public void countEventNames(JoinPoint jp, Ticket ticket, User user, Integer existingDiscount){  
    Map<User, Map<String, Integer>> userMap = counterService.getUserDiscountMap();
    
    Map<String, Integer> discountStrategyMap = new HashMap<String, Integer>();
    if(!userMap.containsKey(user)){
      userMap.put(user, discountStrategyMap);
    }
    discountStrategyMap = userMap.get(user);
   
    String strategyName = ((DiscountStrategy)jp.getTarget()).getName();
    if(!discountStrategyMap.containsKey(strategyName)){
      discountStrategyMap.put(strategyName, 0);
    }
    
    discountStrategyMap.put(strategyName, discountStrategyMap.get(strategyName)+1);
    userMap.put(user, discountStrategyMap);
    counterService.setUserDiscountMap(userMap);
    
   // System.out.println(">>INSIDE ADVICE<< " + jp.getTarget().getClass() + ">>INSIDE ADVICE<< ");
  }
  
}

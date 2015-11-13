package com.epam.sc.aop;

import java.util.Map;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.sc.beans.Identifiable;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.UserTicket;
import com.epam.sc.services.api.CounterService;

@Aspect
@Component
public class CounterAspect {
  
  @Autowired
  protected CounterService<Identifiable> counterService;

  /**
   * How many times each event was accessed  by name
   * Map<Event, Integer>
   */
  @AfterReturning(pointcut = "com.epam.sc.aop.SystemPointcut.serviceEventByNameMethod()", returning = "retVal")
  public void countEventNames(JoinPoint jp, Event retVal){    
    Map<Identifiable, Integer> map = counterService.getAll();  
    Integer newVal = checkAndSaveCounter(map, retVal);
    //System.out.println(">>INSIDE ADVICE<< " + jp.getTarget().getClass() + ">>INSIDE ADVICE<< " + retVal.getName() + ": " +  newVal);
  }
  
  /**
   * How many times its prices were queried
   * Map<Ticket, Integer>
   */
  @AfterReturning(pointcut = "execution(* com.epam.sc.services.api.BookingService.getTicketPrice(..))", returning = "retVal")
  public void countGetPrices(JoinPoint jp, Map<Ticket, Double> retVal){
    Map<Identifiable, Integer> map = counterService.getAll();
    Set<Ticket> setTickets = retVal.keySet();
    for(Ticket t : setTickets){
      Integer newVal = checkAndSaveCounter(map, t);
     // System.out.println(">>INSIDE ADVICE<< " + jp.getTarget().getClass() + ">>INSIDE ADVICE<< " + t.getEventAuditorium()+ ": " +  newVal);
    }
  }
  
  /**
   * How many times its tickets were booked
   * Map<UserTicket, Integer>
   */
  @AfterReturning(pointcut = "execution(* com.epam.sc.services.api.BookingService.bookTicket(..))", returning = "retVal")
  public void countBooked(JoinPoint jp, UserTicket retVal){
    Map<Identifiable, Integer> map = counterService.getAll();
    Integer newVal = checkAndSaveCounter(map, retVal);
   // System.out.println(">>INSIDE ADVICE<< " + jp.getTarget().getClass() + ">>INSIDE ADVICE<< " + retVal + ": " +  newVal);
  }
  
  protected Integer checkAndSaveCounter(Map<Identifiable, Integer> map, Identifiable key){
    if(!map.containsKey(key)){
      map.put(key, 0);
    }
    Integer newVal = map.get(key)+1;
    counterService.save(key, newVal);
    return newVal;
  }
}

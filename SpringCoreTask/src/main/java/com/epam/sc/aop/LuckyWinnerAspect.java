package com.epam.sc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;
import com.epam.sc.services.api.SomeTestAnnotation;
import com.epam.sc.services.api.UserService;
import com.epam.sc.util.RandomUtil;

@Aspect
@Component
public class LuckyWinnerAspect {
  
  @Autowired
  private UserService userService;
  
  @Around(value = "args(ticket, user, finalPrice) && @annotation(someTestAnnotation)", argNames = "ticket, user, finalPrice, someTestAnnotation")
  public Object actionBooked(ProceedingJoinPoint proceedingPoint, Ticket ticket, User user, Double finalPrice, SomeTestAnnotation someTestAnnotation) {
    boolean isLuckyUser = RandomUtil.getRandomBoolean();
    if(isLuckyUser){
      System.out.println("   WOW !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! LUCKY USER: " + user.getName());
      EventAuditorium eventAuditorium = ticket.getEventAuditorium();
      user.setSystemMessage("You are lucky! Your last lucky ticket: Film: " + eventAuditorium.getEvent().getName()
          + ", Auditorium: " + eventAuditorium.getAuditorium().getName()
          + ", Time: " + eventAuditorium.getTime());
      Integer luckyCount = user.getLuckyCount();
      luckyCount = luckyCount == null ? 1 : luckyCount +1;
      user.setLuckyCount(luckyCount);
      userService.updateUser(user);
      finalPrice = 0D;
    } 
    
    Object result = null;
    try {
      result = proceedingPoint.proceed(new Object[] {ticket, user, finalPrice});
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Around(value = "args(ticket, finalPrice) && @annotation(someTestAnnotation)", argNames = "ticket, finalPrice, someTestAnnotation")
  public Object actionBooked(ProceedingJoinPoint proceedingPoint, Ticket ticket, Double finalPrice, SomeTestAnnotation someTestAnnotation) {
    boolean isLuckyUser = RandomUtil.getRandomBoolean();
    if(isLuckyUser){
      System.out.println("   WOW !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! LUCKY USER: " + "unRegister");
      finalPrice = 0D;
    } 
    
    Object result = null;
    try {
      result = proceedingPoint.proceed(new Object[] {ticket, finalPrice});
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return result;
  }

}

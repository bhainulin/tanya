package com.epam.sc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemPointcut {
  
  @Pointcut("execution(* com.epam.sc.*.services.*.*(..))")
  public void serviceMethod() {}

  @Pointcut("execution(* com.epam.sc.*.services.*.save*(..))")
  public void serviceSaveMethod() {}

  @Pointcut("execution(* com.epam.sc.services.api.*.getEventByName(..))")
  public void serviceEventByNameMethod() {}
  
  @Pointcut("within(com.epam.sc.services.api.EventService+)")//EventService+ means EventService and all subclasses / implementing classes.
  public void eventLogicMethods(){}

}

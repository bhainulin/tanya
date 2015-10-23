package com.epam.spring.core.loggers;

import java.util.Collection;

import com.epam.spring.core.beans.Event;

public class CombinedEventLogger implements EventLogger {
  
  Collection<EventLogger> loggers;

  public CombinedEventLogger(Collection<EventLogger> loggers) {
    this.loggers = loggers;
  }

  public void logEvent(Event event) {
    for(EventLogger l : loggers){
      l.logEvent(event);
    }
  }

}

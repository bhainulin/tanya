package com.epam.spring.core;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.loggers.CacheFileEventLogger;
import com.epam.spring.core.loggers.EventLogger;

public class App {

  private Client client;

  private EventLogger eventLogger;
  //private EventLogger defaultLogger = 
  
  private Event event;
  
  private Map<EventType, EventLogger> loggers;

  public App() {
  }

  public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers/*Event event*/) {
    this.client = client;
    this.eventLogger = eventLogger;
    this.loggers = loggers;
   // this.event = event;
  }

  public static void main(String[] args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    App app = (App) ctx.getBean("app");

    app.event.setMsg("Some event for user 1");
    
    app.logEvent(EventType.ERROR, app.event);
  }
  
  private void logEvent(EventType type, Event event) {
    EventLogger logger = loggers.get(type);
    if(logger == null){
      //logger = new CacheFileEventLogger();
    }
    String message = client.getGreeting() + " " + event.getMsg().replaceAll(client.getId()+"", client.getFullName());
    event.setMsg(message);
    logger.logEvent(event);
  }

  public void setEvent(Event event) {
    this.event = event;
  }
  
 /* private void logEvent(Event event, EventType type) {
    String message = event.getMsg().replaceAll(client.getId()+"", client.getFullName());
    event.setMsg(message);
    eventLogger.logEvent(event);
  }
*/
}

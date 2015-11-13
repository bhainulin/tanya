package com.epam.sc.beans;

import java.time.LocalDateTime;

public class EventAuditorium implements Identifiable{
  private Long id;
  private Event event;
  private Auditorium auditorium;
  private LocalDateTime time;
  
  public EventAuditorium() {
  }

  public EventAuditorium(Event event, Auditorium auditorium, LocalDateTime time) {
    this.event = event;
    this.auditorium = auditorium;
    this.time = time;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Auditorium getAuditorium() {
    return auditorium;
  }

  public void setAuditorium(Auditorium auditorium) {
    this.auditorium = auditorium;
  }
  
  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "EventAuditorium [id=" + id + ", event=" + event + ", auditorium=" + auditorium + ", time=" + time + "]";
  }
 
}

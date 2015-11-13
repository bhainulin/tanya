package com.epam.sc.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.repository.EventAuditoriumRepository;
import com.epam.sc.repository.EventRepository;
import com.epam.sc.services.api.EventService;

@Service
public class EventServiceImpl implements EventService{
  
  @Autowired
  // @Qualifier("eventMapRepository")
  @Qualifier("jdbcEventDao")
  private EventRepository eventDao;
  
  @Autowired
  // @Qualifier("eventAuditoriumMapRepository")
  @Qualifier("jdbcEventAuditoriumDao")
  private EventAuditoriumRepository eventAudDao;
  
  public List<Event> getAll(){
    return eventDao.getAll();
  }
  
  public Long createEvent(Event ev){
    return eventDao.createEvent(ev);
  }
  
  public void removeEvent(Long id){
    eventDao.removeEvent(id);
  }
  
  public Event getEventByName(String name){
   return eventDao.getEventByName(name);
  }
  
  public void assignAuditorium(Event event, Auditorium auditorium, LocalDateTime time){
    eventAudDao.createEventAud(new EventAuditorium(event, auditorium, time));
  }
  
  public List<EventAuditorium> getAllEventAuditorium(){
    return eventAudDao.getAll();
  }
  
  public EventAuditorium getEventAuditorium(Event event, LocalDateTime time){
    List<EventAuditorium> list =  eventAudDao.getAll();
    for(EventAuditorium ea : list){
      if(ea.getEvent().equals(event) && ea.getTime().equals(time)){
        return ea;
      }
    }
    return null;
  }

  @Override
  public Event getEventById(Long id) {
    return eventDao.getEventById(id);
  }

}

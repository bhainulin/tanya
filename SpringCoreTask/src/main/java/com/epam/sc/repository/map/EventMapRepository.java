package com.epam.sc.repository.map;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.sc.beans.Event;
import com.epam.sc.repository.EventRepository;

@Repository
public class EventMapRepository extends AbstractMapRepository<Event> implements EventRepository{
  
  public List<Event> getAll(){
    return super.getAll();
  }
  
  public Long createEvent(Event ev){
    return create(ev);
  }
  
  public void removeEvent(Long id){
    remove(id);
  }
  
  public Event getEventByName(String name){
    List<Event> all = getAll();
    for(Event e : all){
      if (e.getName() != null &&  e.getName().equalsIgnoreCase(name)) {
        return e;
      }
    }
    return null;
  }

  @Override
  public Event getEventById(Long Id) {
    // TODO Auto-generated method stub
    return null;
  }

}

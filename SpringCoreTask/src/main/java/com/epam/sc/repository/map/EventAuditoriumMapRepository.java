package com.epam.sc.repository.map;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.repository.EventAuditoriumRepository;

@Repository
public class EventAuditoriumMapRepository extends AbstractMapRepository<EventAuditorium> implements EventAuditoriumRepository{
  
  public List<EventAuditorium> getAll(){
    return super.getAll();
  }
  
  public Long createEventAud(EventAuditorium ev){
    return create(ev);
  }
  
  public void removeEventAud(Long id){
    remove(id);
  }

  @Override
  public EventAuditorium getEventAuditorium(Long id) {
    // TODO Auto-generated method stub
    return null;
  }
}

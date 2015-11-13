package com.epam.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.repository.AuditoriumRepository;
import com.epam.sc.services.api.AuditoriumService;

@Service
public class AuditoriumServiceImpl implements AuditoriumService{
  
  @Autowired
  //@Qualifier("auditoriumPropertyRepository")
  @Qualifier("jdbcAudDao")
  private AuditoriumRepository aRepository;
  
  public List<Auditorium> getAuditoriums(){    
    return aRepository.getAuditoriums();
  }
  
  public Integer getSeatsNumber(String name){
    Auditorium a = getAuditoriumByName(name);
    if(a != null){
      return a.getCountOfSeats();
    }
    throw new IllegalArgumentException("Auditorium with name=" + name + " is not found");
  }
  
  public List<Integer> getVipSeats(String name){
    Auditorium a = getAuditoriumByName(name);
    if(a != null){
      return a.getVipSeats();
    }
    throw new IllegalArgumentException("Auditorium with name=" + name + " is not found");
  }
  
  public Auditorium getAuditoriumByName(String name){
    //TODO: to repository!!!
    for(Auditorium a : getAuditoriums()){
      if(a.getName() != null && a.getName().equalsIgnoreCase(name)){
        return a;
      }
    }
    return null;
  }
  
  public Auditorium getAuditoriumById(Long id){
    return aRepository.getAuditoriumById(id);
  }
}

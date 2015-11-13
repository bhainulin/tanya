package com.epam.sc.repository.property;

import java.util.List;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.repository.AuditoriumRepository;

//@Repository//configure this class in xml
public class AuditoriumPropertyRepository implements AuditoriumRepository{
  
  private List<Auditorium> list;
  
  public AuditoriumPropertyRepository(List<Auditorium> list) {
    this.list = list;
  }

  @Override
  public List<Auditorium> getAuditoriums() {
    return list;
  }

  @Override
  public Auditorium getAuditoriumById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

}

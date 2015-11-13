package com.epam.sc.beans;

import java.util.ArrayList;
import java.util.List;

public class Auditorium implements Identifiable{
  
  private Long id;
  private String name;
  private Integer countOfSeats;
  private List<Integer> vipSeats;

  public Auditorium() {
  }

  public Auditorium(String name, Integer countOfSeats, List<Integer> vipSeats) {
    this.name = name;
    this.countOfSeats = countOfSeats;
    this.vipSeats = vipSeats;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public List<Integer> getVipSeats() {
    if(vipSeats == null){
      return new ArrayList<Integer>();
    }
    return vipSeats;
  }

  public void setVipSeats(List<Integer> vipSeats) {
    this.vipSeats = vipSeats;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCountOfSeats() {
    return countOfSeats;
  }

  public void setCountOfSeats(Integer countOfSeats) {
    this.countOfSeats = countOfSeats;
  }

  @Override
  public String toString() {
    return "Auditorium [id=" + id + ", name=" + name + ", countOfSeats=" + countOfSeats + ", vipSeats=" + vipSeats
        + "]";
  }
}

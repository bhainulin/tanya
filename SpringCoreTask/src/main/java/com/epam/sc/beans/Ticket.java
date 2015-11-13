package com.epam.sc.beans;

public class Ticket implements Identifiable{
  
  private Long id;
  private EventAuditorium eventAuditorium;
  private Integer seat; 
  private Double price;
  private boolean isFree = true;

  public Ticket() {
  }

  public Ticket(EventAuditorium eventAuditorium, Integer seat, Double price, boolean isFree) {
    super();
    this.eventAuditorium = eventAuditorium;
    this.seat = seat;
    this.price = price;
    this.isFree = isFree;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EventAuditorium getEventAuditorium() {
    return eventAuditorium;
  }

  public void setEventAuditorium(EventAuditorium eventAuditorium) {
    this.eventAuditorium = eventAuditorium;
  }

  public Integer getSeat() {
    return seat;
  }

  public void setSeat(Integer seat) {
    this.seat = seat;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean isFree) {
    this.isFree = isFree;
  }

  @Override
  public String toString() {
    return "Ticket [id=" + id + ", event=" + eventAuditorium.getEvent().getName() + "("+eventAuditorium.getEvent().getRating() +")"
        + ", auditorium=" + eventAuditorium.getAuditorium().getName() + ", time=" + eventAuditorium.getTime()
        + ", seat=" + seat + ", price=" + price
        + ", isFree=" + isFree + "]";
  }
}

package com.epam.sc.beans;


public class Event implements Identifiable{

  private Long id;
  private String name;
  private Double ticketBasePrice = 1D;
  private EventRating rating;

  public Event() {
  }

  public Event(String name, EventRating rating) {
    this.name = name;
    this.rating = rating;
  }

  public Event(String name, Double ticketBasePrice, EventRating rating) {
    this.name = name;
    this.ticketBasePrice = ticketBasePrice;
    this.rating = rating;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getTicketBasePrice() {
    return ticketBasePrice;
  }

  public void setTicketBasePrice(Double ticketBasePrice) {
    this.ticketBasePrice = ticketBasePrice;
  }

  public EventRating getRating() {
    return rating;
  }

  public void setRating(EventRating rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Event [id=" + id + ", name=" + name + ", ticketBasePrice=" + ticketBasePrice + ", rating=" + rating + "]";
  }
}

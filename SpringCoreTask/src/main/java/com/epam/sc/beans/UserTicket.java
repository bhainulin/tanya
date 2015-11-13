package com.epam.sc.beans;

public class UserTicket implements Identifiable{
 
  private Long id;
  private User user;
  private Ticket ticket;
  private Double finalPrice;

  public UserTicket() {
  }

  public UserTicket(User user, Ticket ticket, Double finalPrice) {
    this.user = user;
    this.ticket = ticket;
    this.finalPrice = finalPrice;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Ticket getTicket() {
    return ticket;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  public Double getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(Double finalPrice) {
    this.finalPrice = finalPrice;
  }

  @Override
  public String toString() {
    return "UserTicket [id=" + id + ", user=" + user + ", ticket=" + ticket + ", finalPrice=" + finalPrice + "]";
  }
}

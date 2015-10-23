package com.epam.spring.core.beans;

public class Client {
  
  private Integer id;
  private String fullName;
  private String greeting;
 
  public Client() {
    
  }

  public Client(Integer id, String fullName) {
    this.id = id;
    this.fullName = fullName;
  }

  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
}

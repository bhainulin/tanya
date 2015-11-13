package com.epam.sc.beans;

import java.time.LocalDate;

public class User implements Identifiable{

  private Long id;
  private String name;
  private String email;
  private LocalDate birthday;
  private String systemMessage;
  private Integer luckyCount = 0;

  public User() {
  }

  public User(String name, String email, LocalDate birthday) {
    this.name = name;
    this.email = email;
    this.birthday = birthday;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getSystemMessage() {
    return systemMessage;
  }

  public void setSystemMessage(String systemMessage) {
    this.systemMessage = systemMessage;
  }

  public Integer getLuckyCount() {
    return luckyCount;
  }

  public void setLuckyCount(Integer luckyCount) {
    this.luckyCount = luckyCount;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", email=" + email + ", birthday=" + birthday + ", systemMessage="
        + systemMessage + ", luckyCount=" + luckyCount + "]";
  }
}

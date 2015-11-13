package com.epam.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;
import com.epam.sc.repository.UserRepository;
import com.epam.sc.repository.UserTicketRepository;
import com.epam.sc.services.api.UserService;

@Service
public class UserServiceImpl implements UserService{
  
  @Autowired
  //@Qualifier("userMapRepository")
  @Qualifier("jdbcUserDao")
  private UserRepository dao;
  
  @Autowired
  private UserTicketRepository userTicketRepository;
  

  public List<User> getAllUsers(){
    return dao.getAll();
  }
  
  public List<User> getLuckyUsers(){
    return dao.getLuckyUsers();
  }
  
  public Long registerUser(User user){
    return dao.registerUser(user);
  }
  
  public void updateUser(User user){
    dao.updateUser(user);
  }
  
  public void removeUser(Long id) {
    dao.removeUser(id);
  }
  
  public void removeUser(User user) {
    if(user.getId() != null){
      dao.removeUser(user.getId());
    }
  }
  
  public User getUserById(Long id){
    return dao.getUserById(id);
  }
  
  public User getUserByName(String name){
    return dao.getUserByName(name);
  }
  
  public User getUserByEmail(String email){
    return dao.getUserByEmail(email);
  }
  
  public List<UserTicket> getBookedTickets(User user) {
    List<UserTicket> userTick = userTicketRepository.getUserTicket(user);
    return userTick; 
  }

}

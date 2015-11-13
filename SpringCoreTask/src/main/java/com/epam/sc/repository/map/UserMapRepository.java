package com.epam.sc.repository.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.sc.beans.User;
import com.epam.sc.repository.UserRepository;

@Repository
public class UserMapRepository extends AbstractMapRepository<User> implements UserRepository{
   
  public List<User> getAll(){
    return super.getAll();
  }
  
  public List<User> getLuckyUsers() {
    List<User> result = new ArrayList<User>();
    List<User> users = getAll();
    for(User u : users){
      if (u.getSystemMessage() != null && !u.getSystemMessage().isEmpty()) {
        result.add(u);
      }
    }
    return result;
  }
  
  
  public Long registerUser(User user){
    return create(user);
  }
  
  public void updateUser(User user){
    update(user);
  }
  
  public void removeUser(Long id) {
    remove(id);
  }
  
  public User getUserById(Long id){
    return get(id);
  }
  
  public User getUserByName(String name){
    List<User> users = getAll();
    for(User u : users){
      if (u.getName() != null &&  u.getName().equalsIgnoreCase(name)) {
        return u;
      }
    }
    return null;
  }
  
  public User getUserByEmail(String email){
    List<User> users = getAll();
    for(User u : users){
      if (u.getEmail() != null &&  u.getEmail().equalsIgnoreCase(email)) {
        return u;
      }
    }
    return null;
  }
}

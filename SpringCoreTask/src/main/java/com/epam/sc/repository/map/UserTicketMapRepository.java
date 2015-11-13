package com.epam.sc.repository.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;
import com.epam.sc.repository.UserTicketRepository;

@Repository
public class UserTicketMapRepository extends AbstractMapRepository<UserTicket> implements UserTicketRepository{
  
  public List<UserTicket> getAll(){
    return super.getAll();
  }
  
  public Long createUserTicket(UserTicket ev){
    return create(ev);
  }
  
  public void removeUserTicket(Long id){
    remove(id);
  }
  
  public List<UserTicket> getUserTicket(User u){
    List<UserTicket> list = getAll();
    List<UserTicket> result = new ArrayList<UserTicket>();
    for(UserTicket ut : list){
      if(ut.getUser().equals(u)){
        result.add(ut);
      }
    }
    return result;
  }

}

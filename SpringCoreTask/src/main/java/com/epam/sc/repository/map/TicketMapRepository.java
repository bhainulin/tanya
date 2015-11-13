package com.epam.sc.repository.map;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.sc.beans.Ticket;
import com.epam.sc.repository.TicketRepository;

@Repository
public class TicketMapRepository extends AbstractMapRepository<Ticket> implements TicketRepository{
  
  public List<Ticket> getAll(){
    return super.getAll();
  }
  
  public Long createTicket(Ticket ev){
    return create(ev);
  }
  
  public void removeTicket(Long id){
    remove(id);
  }
  
  public void updateTicket(Ticket t){
    update(t);
  }

}

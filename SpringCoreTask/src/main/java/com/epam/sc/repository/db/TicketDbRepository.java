package com.epam.sc.repository.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.beans.Ticket;
import com.epam.sc.repository.AuditoriumRepository;
import com.epam.sc.repository.EventAuditoriumRepository;
import com.epam.sc.repository.EventRepository;
import com.epam.sc.repository.TicketRepository;


@Repository
@Qualifier(value="jdbcTicketDao")
public class TicketDbRepository implements TicketRepository{
  
  @Autowired
  @Qualifier("jdbcEventAuditoriumDao")
  private EventAuditoriumRepository eventAudDao;
  
  @Autowired
  @Qualifier("jdbcEventDao")
  private EventRepository eventDao;
  
  @Autowired
  @Qualifier("jdbcAudDao")
  private AuditoriumRepository aRepository;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  public TicketDbRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }
  
  @Override
  public List<Ticket> getAll() {
    boolean isLazyLoading = false;
    Map<String, Object> params = new HashMap<String, Object>();   
    String sql = "SELECT * FROM Ticket";
    List<Ticket> result = namedParameterJdbcTemplate.query(sql, params, new TicketMapper());
    if(!isLazyLoading){
      for(Ticket t : result){
       populate(t);
      }
    }
    return result;
  }

  @Override
  public Long createTicket(Ticket t) {
    jdbcTemplate.update("INSERT INTO Ticket (EVENTAUDITORIUMID, SEAT, PRICE, ISFREE )VALUES (?, ?, ?, ? )", 
        t.getEventAuditorium().getId(), t.getSeat(), t.getPrice(), t.isFree());
    return null;
  }

  @Override
  public void removeTicket(Long id) {
    String sql = "DELETE FROM Ticket WHERE id=?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public void updateTicket(Ticket t) {
    String sql = "UPDATE Ticket SET EVENTAUDITORIUMID=?, SEAT=?, PRICE=?, ISFREE=? WHERE id=?";
    jdbcTemplate.update(sql, t.getEventAuditorium().getId(), t.getSeat(), t.getPrice(), t.isFree(), t.getId());
  }
  
  private static final class TicketMapper implements RowMapper<Ticket> {

    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
      Ticket result = new Ticket();
      result.setId(rs.getLong("id"));
      result.setSeat(rs.getInt("seat"));
      result.setPrice(rs.getDouble("price"));
      result.setFree(rs.getBoolean("isFree"));
      EventAuditorium eventAuditorium = new EventAuditorium();
      eventAuditorium.setId(rs.getLong("eventAuditoriumId"));
      result.setEventAuditorium(eventAuditorium);
      return result;
    }
  }
  
  private void populate(Ticket t){
    Long evAudId = t.getEventAuditorium().getId();
    EventAuditorium eventAuditorium = eventAudDao.getEventAuditorium(evAudId);
    populate(eventAuditorium);
    t.setEventAuditorium(eventAuditorium);
  }
  
  private void populate(EventAuditorium eventAuditorium){
    Long eventId = eventAuditorium.getEvent().getId();
    Long audId = eventAuditorium.getAuditorium().getId();
    Event event =  eventDao.getEventById(eventId);
    Auditorium auditorium = aRepository.getAuditoriumById(audId);
    eventAuditorium.setAuditorium(auditorium);
    eventAuditorium.setEvent(event);
  }

}

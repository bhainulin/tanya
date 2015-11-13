package com.epam.sc.repository.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.epam.sc.repository.AuditoriumRepository;
import com.epam.sc.repository.EventAuditoriumRepository;
import com.epam.sc.repository.EventRepository;

@Repository
@Qualifier(value="jdbcEventAuditoriumDao")
public class EventAuditoriumDbRepository implements EventAuditoriumRepository {
  
  @Autowired
  @Qualifier("jdbcEventDao")
  private EventRepository eventDao;
  
  @Autowired
  @Qualifier("jdbcAudDao")
  private AuditoriumRepository aRepository;
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  public EventAuditoriumDbRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<EventAuditorium> getAll() {
    String sql = "SELECT * FROM EventAuditorium";
    List<EventAuditorium> result = jdbcTemplate.query(sql,  new EventAuditoriumMapper());
    //TODO: lazyloading = false
    for(EventAuditorium eventAuditorium : result){
      populate(eventAuditorium);
    }
    return result;
  }
  
  @Override
  public EventAuditorium getEventAuditorium(Long id) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);
    String sql = "SELECT * FROM EventAuditorium WHERE id=:id";
    EventAuditorium result = namedParameterJdbcTemplate.queryForObject(sql, params, new EventAuditoriumMapper());
    populate(result);
    return result;
  }

  @Override
  public Long createEventAud(EventAuditorium ev) {
    jdbcTemplate.update("INSERT INTO EventAuditorium (eventId, auditoriumId, timeEvent) VALUES (?, ?, ?)", 
       ev.getEvent().getId(), ev.getAuditorium().getId(), Timestamp.valueOf(ev.getTime()));
    return null;
  }

  @Override
  public void removeEventAud(Long id) {
    String sql = "DELETE FROM EventAuditorium WHERE id=?";
    jdbcTemplate.update(sql, id);
  }
  
  private static final class EventAuditoriumMapper implements RowMapper<EventAuditorium> {

    public EventAuditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
      EventAuditorium result = new EventAuditorium();
      result.setId(rs.getLong("id"));
      Event event = new Event();
      event.setId(rs.getLong("eventId"));
      result.setEvent(event);
      Auditorium auditorium = new Auditorium();
      auditorium.setId(rs.getLong("auditoriumId"));
      result.setAuditorium(auditorium);
      result.setTime(rs.getTimestamp("timeEvent").toLocalDateTime());
      return result;
    }
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

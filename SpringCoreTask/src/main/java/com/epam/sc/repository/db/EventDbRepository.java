package com.epam.sc.repository.db;

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

import com.epam.sc.beans.Event;
import com.epam.sc.beans.EventRating;
import com.epam.sc.repository.EventRepository;

@Repository
@Qualifier(value="jdbcEventDao")
public class EventDbRepository implements EventRepository{
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  public EventDbRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Event> getAll() {
    String sql = "SELECT * FROM event";
    List<Event> result = jdbcTemplate.query(sql,  new EventMapper());
    return result;
  }

  @Override
  public Long createEvent(Event ev) {
    jdbcTemplate.update("INSERT INTO event (name, ticketBasePrice, rating) VALUES (?, ?, ?)", 
        ev.getName(), ev.getTicketBasePrice(), ev.getRating().toString());
    return null;
  }

  @Override
  public void removeEvent(Long id) {
    String sql = "DELETE FROM event WHERE id=?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public Event getEventByName(String name) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("name", name);
    String sql = "SELECT * FROM event WHERE name=:name";
    Event result = namedParameterJdbcTemplate.queryForObject(sql, params, new EventMapper());
    return result;
  }
  

  @Override
  public Event getEventById(Long id) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);
    String sql = "SELECT * FROM event WHERE id=:id";
    Event result = namedParameterJdbcTemplate.queryForObject(sql, params, new EventMapper());
    return result;
  }

  
  private static final class EventMapper implements RowMapper<Event> {

    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
      Event resut = new Event();
      resut.setId(rs.getLong("id"));
      resut.setName(rs.getString("name"));
      resut.setTicketBasePrice(rs.getDouble("ticketBasePrice"));
      resut.setRating(EventRating.valueOf(rs.getString("rating")));     
      return resut;
    }
  }
}

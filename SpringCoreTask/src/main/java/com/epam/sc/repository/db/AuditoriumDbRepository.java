package com.epam.sc.repository.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.User;
import com.epam.sc.repository.AuditoriumRepository;

@Repository
@Qualifier(value="jdbcAudDao")
public class AuditoriumDbRepository implements AuditoriumRepository{

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  public AuditoriumDbRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Auditorium> getAuditoriums() {
    String sql = "SELECT * FROM Auditorium";
    List<Auditorium> result = jdbcTemplate.query(sql,  new AuditoriumMapper());
    return result;
  }
  
  @Override
  public Auditorium getAuditoriumById(Long id) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);
    String sql = "SELECT * FROM Auditorium WHERE id=:id";
    Auditorium result = namedParameterJdbcTemplate.queryForObject(sql, params, new AuditoriumMapper());
    return result;
  }
  
  private static final class AuditoriumMapper implements RowMapper<Auditorium> {

    public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
      Auditorium result = new Auditorium();
      result.setId(rs.getLong("id"));
      result.setName(rs.getString("name"));
      result.setCountOfSeats(rs.getInt("countOfSeats"));
      String vips = rs.getString("vipSeats");
       
      List<Integer> vipSeats = new ArrayList<Integer>();
      if(vips != null && !vips.isEmpty()){
        vipSeats = Arrays.asList(vips.split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
      }
    
      result.setVipSeats(vipSeats);
      return result;
    }
  }

}

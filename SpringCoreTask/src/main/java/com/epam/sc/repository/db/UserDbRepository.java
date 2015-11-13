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

import com.epam.sc.beans.User;
import com.epam.sc.repository.UserRepository;

@Repository
@Qualifier(value="jdbcUserDao")
public class UserDbRepository implements UserRepository{
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  public UserDbRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<User> getAll() {
    String sql = "SELECT * FROM users";
    List<User> result = jdbcTemplate.query(sql,  new UserMapper());
    return result;
  }

  @Override
  public List<User> getLuckyUsers() {
    Map<String, Object> params = new HashMap<String, Object>();   
    String sql = "SELECT * FROM users WHERE luckyCount > 0";
    List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
    return result;
  }

  @Override
  public Long registerUser(User user) {
    jdbcTemplate.update("INSERT INTO users (name, email, birthday, systemMessage, luckyCount) VALUES (?, ?, ?, ?, ?)", 
        user.getName(), user.getEmail(), Date.valueOf(user.getBirthday()), user.getSystemMessage(), user.getLuckyCount());
    return null;
  }

  @Override
  public void updateUser(User user) {
   String sql = "UPDATE users SET name=?, email=?, birthday=?, systemMessage=?, luckyCount=? WHERE id=?";
   jdbcTemplate.update(sql, user.getName(), user.getEmail(), Date.valueOf(user.getBirthday()), user.getSystemMessage(), user.getLuckyCount(), user.getId());
  }

  @Override
  public void removeUser(Long id) {
    String sql = "DELETE FROM users WHERE id=?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public User getUserById(Long id) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);
    String sql = "SELECT * FROM users WHERE id=:id";
    User result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
    return result;
  }

  @Override
  public User getUserByName(String name) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("name", name);
    String sql = "SELECT * FROM users WHERE name=:name";
    User result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
    return result;
  }

  @Override
  public User getUserByEmail(String email) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("email", email);
    String sql = "SELECT * FROM users WHERE email=:email";
    User result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
    return result;
  }
  
  private static final class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User result = new User();
      result.setId(rs.getLong("id"));
      result.setName(rs.getString("name"));
      result.setEmail(rs.getString("email"));
      result.setSystemMessage(rs.getString("systemMessage"));
      result.setBirthday(rs.getDate("birthday").toLocalDate());
      result.setLuckyCount(rs.getInt("luckyCount"));
      return result;
    }
  }

}

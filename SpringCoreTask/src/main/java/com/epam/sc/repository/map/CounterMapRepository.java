package com.epam.sc.repository.map;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.sc.beans.Identifiable;
import com.epam.sc.beans.User;
import com.epam.sc.repository.CounterRepository;

@Repository
public class CounterMapRepository<T extends Identifiable> implements CounterRepository{ 
  
  protected Map<T, Integer> map = new HashMap<T, Integer>();
  
  private Map<User, Map<String, Integer>> userDiscountMap = new HashMap<User, Map<String,Integer>>();
  
  public CounterMapRepository() {
     
  }

  @Override
  public Integer getValue(Identifiable entity) {
    return map.get(entity);
  }

  @Override
  public void save(Identifiable entity, Integer count) {
    map.put((T) entity, count);    
  }

  @Override
  public Map<T, Integer> getAll() {
    return map;
  }

  @Override
  public Map getUserDiscountMap() {
    return userDiscountMap;
  }

  @Override
  public void setUserDiscountMap(Map userDiscountMap) {
    this.userDiscountMap = userDiscountMap;    
  }
}


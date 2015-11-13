package com.epam.sc.repository;

import java.util.Map;

import com.epam.sc.beans.Identifiable;
import com.epam.sc.beans.User;

/**
 * 
 * Class is used for store statistic
 *
 * @param <T>
 */
public interface CounterRepository<T extends Identifiable>{

  public Integer getValue(Identifiable entity);

  public void save(Identifiable entity, Integer count);

  public Map<T, Integer> getAll();
  
  public Map<User, Map<String, Integer>> getUserDiscountMap();

  public void setUserDiscountMap(Map<User, Map<String, Integer>> userDiscountMap);
  
 
}

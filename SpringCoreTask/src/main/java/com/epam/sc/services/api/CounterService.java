package com.epam.sc.services.api;

import java.util.Map;

import com.epam.sc.beans.Identifiable;
import com.epam.sc.beans.User;

/**
 * 
 * Service is used for statistic
 *
 * @param <T>
 */
public interface CounterService<T extends Identifiable> {
  
  public Integer getValue(T entity);

  public void save(T entity, Integer count);

  public Map<T, Integer> getAll();
  
  public Map<User, Map<String, Integer>> getUserDiscountMap();

  public void setUserDiscountMap(Map<User, Map<String, Integer>> userDiscountMap);

}
